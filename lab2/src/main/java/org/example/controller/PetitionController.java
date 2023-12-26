package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.model.Petition;
import org.example.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping("/petition")
public class PetitionController {

    @Autowired
    private PetitionService petitionService;

    @GetMapping
    public String queryPetitions(Model model) {
        List<Petition> petitionList = petitionService.getPetitions();
        model.addAttribute("petitions", petitionList);
        return "petitions";
    }

    @GetMapping("/{id}")
    public String getPetition(@PathVariable("id") String id, Model model, HttpServletRequest request){
        Petition petition = petitionService.getPetition(id);
        model.addAttribute("petition", petition);
        model.addAttribute("link", request.getRequestURL());
        return "petition-view";
    }

    @PostMapping
    public String createPetition(Model model, Petition petition, BindingResult result, HttpServletRequest request) {
        Petition petition1 = petitionService.createPetition(petition.getName(), petition.getDescription());
        model.addAttribute("petition", petition1);
        model.addAttribute("link", request.getRequestURL()+"/"+petition1.getId());
        return "petition-view";
    }

    @PostMapping("/delete/{id}")
    public String deletePetition(@PathVariable("id") String id, Model model) {

        petitionService.deletePetition(id);
        return "redirect:/";
    }

    @PostMapping("/vote/{id}")
    public String vote(@PathVariable("id") String id, Model model, HttpServletRequest request) {
        Petition petition = petitionService.addVote(id);
        model.addAttribute("petition", petition);
        model.addAttribute("link", request.getRequestURL().toString().replace("vote/", ""));
        return "petition-view";
    }
}
