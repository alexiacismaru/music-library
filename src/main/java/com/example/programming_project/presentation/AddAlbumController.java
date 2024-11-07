package com.example.programming_project.presentation;

import com.example.programming_project.domain.Albums;
import com.example.programming_project.exceptions.DestinationNotAvailableException;
import com.example.programming_project.presentation.viewModel.AlbumViewModel;
import com.example.programming_project.service.AlbumService;
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
@RequestMapping("/addAlbum")
@Profile("web")
public class AddAlbumController {
    private AlbumService serviceClass;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public AddAlbumController(AlbumService serviceClass) {
        this.serviceClass = serviceClass;
    }

    @GetMapping
    public String showAddAlbumForm(Model model, HttpSession httpSession) {
        model.addAttribute("albumViewModel", new AlbumViewModel());

        String url = "/addAlbum";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "addAlbum";
    }

    @PostMapping
    public String processAddAlbum(HttpSession httpSession, @Valid @ModelAttribute("albumViewModel") AlbumViewModel albumViewModel, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                logger.error(error.toString());
            });
            return "addAlbum";
        }


        try {
            logger.debug("Creating a new album...");
            Albums album = new Albums(albumViewModel.getTitle(), albumViewModel.getAmountOfSongs(), albumViewModel.getReleaseDate());
            serviceClass.addAlbum(album);
            httpSession.setAttribute("album", album);
        } catch (DestinationNotAvailableException e) {
            logger.error("Error when trying to access the page:" + e.getMessage());
            return "error";
        }

        return "redirect:/albums";
    }

    @ExceptionHandler(InvalidPropertyException.class)
    public String handleInvalidInput(Exception e, Model model){
        logger.error(e.getMessage());
        model.addAttribute("error", "Invalid input...");
        return "error";
    }
}
