package com.guillaumegasnier.education.web.controllers;

import com.guillaumegasnier.education.web.services.RechercheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/recherche")
public class RechercheController {

    private final RechercheService rechercheService;

    @Autowired
    public RechercheController(RechercheService rechercheService) {
        this.rechercheService = rechercheService;
    }

    @GetMapping("")
    public String getResultatRecherche(@RequestParam MultiValueMap<String, String> facettes, Model model) throws IOException {
        model.addAttribute("q", facettes.getFirst("q") != null ? facettes.getFirst("q") : "");
        model.addAttribute("resultats", rechercheService.recherche(facettes));
        model.addAttribute("facettes", rechercheService.facette(facettes));
        return "recherche/resultats";
    }
}
