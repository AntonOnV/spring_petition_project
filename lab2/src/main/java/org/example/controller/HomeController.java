package org.example.controller;

import org.example.model.Petition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/create")
    public String createPetition(Model model) {
        model.addAttribute("petition", new Petition());
        return "petition-create";
    }
}
