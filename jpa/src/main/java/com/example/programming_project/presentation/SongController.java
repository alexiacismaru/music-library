package com.example.programming_project.presentation;

import com.example.programming_project.domain.Songs;
import com.example.programming_project.presentation.viewModel.SongViewModel;
import com.example.programming_project.service.SongService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Profile("web")
public class SongController {
    private SongService serviceClass;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SongController(SongService serviceClass) {
        this.serviceClass = serviceClass;
    }

    @GetMapping("/songs")
    public String showSongs(Model model, HttpSession httpSession) {
        logger.info("Showing songs...");
        List<Songs> songs = serviceClass.getAllSongs();
        model.addAttribute("songs", songs);

        String url = "/songs";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "allSongs";
    }

    @GetMapping("song/{id}")
    public String showOneSong(@PathVariable int id, Model model, HttpSession httpSession) {
        System.out.println("Loading song by id...");
        model.addAttribute("song", serviceClass.getSong(id));
        String url = "/song";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "song";
    }

    @PostMapping("song/{id}")
    public String processOneSong(@PathVariable String id, @Valid @ModelAttribute("songViewModel")
    SongViewModel songViewModel, Model model) {
        model.addAttribute("id", id);
        return "redirect:/song/{id}";
    }
}
