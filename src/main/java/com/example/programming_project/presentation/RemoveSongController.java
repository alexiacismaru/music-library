package com.example.programming_project.presentation;

import com.example.programming_project.presentation.viewModel.SongViewModel;
import com.example.programming_project.service.SongService;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/removeSong")
@Profile("web")
public class RemoveSongController {
    private SongService songService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public RemoveSongController(SongService songService) {
        this.songService = songService;
    }
    @GetMapping
    public String showRemoveSongForm(Model model, HttpSession httpSession) {
        model.addAttribute("songViewModel", new SongViewModel());

        String url = "/removeSong";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "removeSong";
    }

    @PostMapping
    public String removeSong(int id, HttpSession httpSession) {
        logger.debug("Deleting song...");
        songService.deleteSong(id);
        httpSession.removeAttribute("song");
        return "redirect:/songs";
    }
}
