package com.example.programming_project.controllers;

import com.example.programming_project.domain.Artist;
import com.example.programming_project.exceptions.DestinationNotAvailableException;
import com.example.programming_project.controllers.viewModel.ArtistViewModel;
import com.example.programming_project.service.ArtistService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
@RequestMapping("/addArtist")
@Profile("web")
public class AddArtistController {
    private ArtistService serviceClass;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AddArtistController(ArtistService serviceClass) {
        this.serviceClass = serviceClass;
    }

    @GetMapping
    public String showAddArtistForm(Model model, HttpSession httpSession) {
        model.addAttribute("artistViewModel", new ArtistViewModel());

        String url = "/addArtist";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "addArtist";
    }

    @PostMapping
    public String processAddArtist(HttpSession httpSession, @Valid @ModelAttribute("artistViewModel") ArtistViewModel artistViewModel, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                logger.error(error.toString());
            });
            return "addArtist";
        }


        try {
            Artist artist = new Artist(artistViewModel.getName(), artistViewModel.getProfile(), artistViewModel.getDescription());
            serviceClass.addArtist(artist);
            logger.debug("Creating a new artist...");
            httpSession.setAttribute("artist", artist);
        } catch (DestinationNotAvailableException e) {
            logger.error("Error when trying to access the page:" + e.getMessage());
            return "error";
        }

        return "redirect:/artists";
    }

    @ExceptionHandler(InvalidPropertyException.class)
    public String handleInvalidInput(Exception e, Model model){
        logger.error(e.getMessage());
        model.addAttribute("error", "Invalid input...");
        return "error";
    }
}
