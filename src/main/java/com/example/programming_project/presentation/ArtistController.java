package com.example.programming_project.presentation;

import com.example.programming_project.presentation.viewModel.ArtistViewModel;
import com.example.programming_project.service.ArtistService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@Profile("web")
public class ArtistController {
    private ArtistService serviceClass;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ArtistController(ArtistService serviceClass) {
        this.serviceClass = serviceClass;
    }

    @GetMapping("/artists")
    public String showArtist(Model model, HttpSession httpSession) {
        logger.info("Showing artists...");
        model.addAttribute("artists", serviceClass.getAllArtists());

        String url = "/artists";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "allArtists";
    }

    @GetMapping("artist/{id}")
    public String showOneArtist(@PathVariable int id, Model model, HttpSession httpSession) {
        System.out.println("Loading artist by id...");
        model.addAttribute("artist", serviceClass.getArtistWithAlbums(id));

        String url = "/artist";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "artist";
    }

    @PostMapping("artist/{id}")
    public String processOneArtist(@PathVariable String id, @Valid @ModelAttribute("artistViewModel")
    ArtistViewModel artistViewModel, Model model) {
        model.addAttribute("id", id);
        return "redirect:/artist/{id}";
    }
}
