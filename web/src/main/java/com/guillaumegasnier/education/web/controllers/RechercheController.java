package com.guillaumegasnier.education.web.controllers;

import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.services.CoreRechercheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/recherche")
public class RechercheController {

    private final CoreRechercheService coreRechercheService;

    @Autowired
    public RechercheController(CoreRechercheService coreRechercheService) {
        this.coreRechercheService = coreRechercheService;
    }

    @GetMapping("")
    public String getResultatRecherche(@RequestParam MultiValueMap<String, String> facettes, Model model) {
        //model.addAttribute("q", facettes.getFirst("q") != null ? facettes.getFirst("q") : "");
        //model.addAttribute("resultats", coreRechercheService.searchEtablissements(new RechercheCriteria(facettes)));
        model.addAttribute("recherche", coreRechercheService.recherche(new RechercheCriteria(facettes)));
        return "recherche/resultats";
    }
}
