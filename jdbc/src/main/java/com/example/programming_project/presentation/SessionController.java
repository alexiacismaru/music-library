package com.example.programming_project.presentation;

import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/session")
@Profile("web")
public class SessionController {
    public Set<String> attributes(HttpSession session) {
        TreeSet<String> attributes = new TreeSet<>();
        Enumeration<String> enumeration = session.getAttributeNames();

        while (enumeration.hasMoreElements()) {
            attributes.add(enumeration.nextElement());
        }
        return attributes;
    }

    @GetMapping
    String showHistory(Model model, HttpSession session) {
        Set<String> sessions = attributes(session);
        List<String> urls = new ArrayList<>();
        Iterator<String> itr = sessions.iterator();

        model.addAttribute("sessions", sessions);

        while (itr.hasNext()) {
            urls.add((String) session.getAttribute(itr.next()));
        }

        model.addAttribute("url", urls);
        return "/session";
    }
}
