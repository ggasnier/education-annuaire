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
        RechercheCriteria critere = new RechercheCriteria(facettes);

        model.addAttribute("critere", critere);
        model.addAttribute("title", "Résultats de la recherche " + critere.getQ());

        return switch (critere.getType()) {
            case "metier" -> {
                model.addAttribute("recherche", coreRechercheService.rechercheMetiers(critere));
                yield "recherche/metiers";
            }
            case "competence" -> {
                model.addAttribute("recherche", coreRechercheService.rechercheCompetences(critere));
                yield "recherche/competences";
            }
            case "certification" -> {
                model.addAttribute("recherche", coreRechercheService.rechercheCertifications(critere));
                yield "recherche/certifications";
            }
            case "formation" -> {
                model.addAttribute("recherche", coreRechercheService.rechercheFormations(critere));
                yield "recherche/formations";
            }
            default -> {
                model.addAttribute("recherche", coreRechercheService.rechercheEtablissements(critere));
                yield "recherche/etablissements";
            }
        };

    }
}
