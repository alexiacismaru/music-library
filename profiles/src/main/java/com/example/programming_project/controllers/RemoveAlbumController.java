package com.example.programming_project.controllers;

import com.example.programming_project.controllers.viewModel.AlbumViewModel;
import com.example.programming_project.service.AlbumService;
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
@RequestMapping("/removeAlbum")
@Profile("web")
public class RemoveAlbumController {
    private AlbumService serviceClass;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RemoveAlbumController(AlbumService serviceClass) {
        this.serviceClass = serviceClass;
    }

    @GetMapping
    public String showRemoveAlbumForm(Model model, HttpSession httpSession) {
        model.addAttribute("albumViewModel", new AlbumViewModel());

        String url = "/removeAlbum";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "removeAlbum";
    }

    @PostMapping
    public String removeAlbum(int id, HttpSession httpSession){
        logger.debug("Deleting album...");
        serviceClass.deleteAlbum(id);
        httpSession.removeAttribute("album");
        return "redirect:/albums";
    }
}
