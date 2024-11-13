package com.example.programming_project.presentation;

import com.example.programming_project.presentation.viewModel.ArtistViewModel;
import com.example.programming_project.service.ArtistService;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("removeArtist")
@Profile("web")
public class RemoveArtistController {
    private ArtistService serviceClass;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RemoveArtistController(ArtistService serviceClass) {
        this.serviceClass = serviceClass;
    }

    @GetMapping
    public String showRemoveArtistForm(Model model, HttpSession httpSession) {
        model.addAttribute("artistViewModel", new ArtistViewModel());

        String url = "/removeArtist";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "removeArtist";
    }

    @PostMapping
    public String removeArtist(int id, HttpSession httpSession){
        logger.debug("Deleting artist...");
        serviceClass.deleteArtist(id);
        httpSession.removeAttribute("artist");
        return "redirect:/artists";
    }
}
