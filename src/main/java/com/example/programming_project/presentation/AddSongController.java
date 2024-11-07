package com.example.programming_project.presentation;

import com.example.programming_project.domain.Songs;
import com.example.programming_project.exceptions.DestinationNotAvailableException;
import com.example.programming_project.presentation.viewModel.SongViewModel;
import com.example.programming_project.service.SongService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/addSong")
@Profile("web")
public class AddSongController {
    private SongService serviceClass;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AddSongController(SongService serviceClass) {
        this.serviceClass = serviceClass;
    }

    @GetMapping
    public String showAddSongForm(Model model, HttpSession httpSession) {
        model.addAttribute("songViewModel", new SongViewModel());

        String url = "/addSong";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "addSong";
    }

    @PostMapping
    public String processAddSong(HttpSession httpSession, @Valid @ModelAttribute("songViewModel") SongViewModel songViewModel, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                logger.error(error.toString());
            });
            return "addSong";
        }

        try {
            logger.debug("Creating a new song...");
            Songs song = new Songs(songViewModel.getSongName(), songViewModel.getDuration(), songViewModel.getGenre());
            serviceClass.addSong(song);
            httpSession.setAttribute("song", song);
        } catch (DestinationNotAvailableException e) {
            logger.error("Error when trying to access the page:" + e.getMessage());
            return "error";
        }

        return "redirect:/songs";
    }

    @ExceptionHandler(InvalidPropertyException.class)
    public String handleInvalidInput(Exception e, Model model){
        logger.error(e.getMessage());
        model.addAttribute("error", "Invalid input...");
        return "error";
    }
}
