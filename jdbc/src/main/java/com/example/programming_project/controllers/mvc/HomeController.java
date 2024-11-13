package com.example.programming_project.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/musicLibrary")
    public String homePage() {
        return "index";
    }
}
