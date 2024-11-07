package com.example.programming_project.presentation;

import com.example.programming_project.domain.Albums;
import com.example.programming_project.presentation.viewModel.AlbumViewModel;
import com.example.programming_project.service.AlbumService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Profile("web")
public class AlbumController {
    private AlbumService serviceClass;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AlbumController(AlbumService serviceClass) {
        this.serviceClass = serviceClass;
    }

    @GetMapping("/albums")
    public String showAlbum(Model model, HttpSession httpSession) {
        logger.info("Showing albums...");
        List<Albums> albums = serviceClass.getAllAlbums();
        model.addAttribute("albums", albums);

        String url = "/albums";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "allAlbums";
    }

    @GetMapping("album/{id}")
    public String showOneAlbum(@PathVariable int id, Model model, HttpSession httpSession) {
        System.out.println("Loading album by id...");
        model.addAttribute("album", serviceClass.getAlbumWithSongs(id));

        String url = "/album";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "album";
    }

    @PostMapping("album/{id}")
    public String processOneAlbum(@PathVariable String id, @Valid @ModelAttribute("albumViewModel") AlbumViewModel albumViewModel, @NotNull Model model) {
        model.addAttribute("id", id);
        return "redirect:/album/{id}";
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {
        return "dberror";
    }
}
