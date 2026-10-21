package com.guillaumegasnier.education.web.controllers;

import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.services.CoreRechercheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/recherche")
public class RechercheController extends WebController {

    private final CoreRechercheService coreRechercheService;

    @GetMapping("")
    public String getResultatRecherche(@RequestParam MultiValueMap<@NonNull String, String> facettes, Model model) {
        RechercheCriteria critere = new RechercheCriteria(facettes);

        model.addAttribute("critere", critere);
        model.addAttribute("title", "Résultats de la recherche " + critere.getQ());

        return switch (critere.getType()) {
            case "metier" -> {
                model.addAttribute(RECHERCHE, coreRechercheService.rechercheMetiers(critere));
                yield "recherche/metiers";
            }
            case "competence" -> {
                model.addAttribute(RECHERCHE, coreRechercheService.rechercheCompetences(critere));
                yield "recherche/competences";
            }
            case "certification" -> {
                model.addAttribute(RECHERCHE, coreRechercheService.rechercheCertifications(critere));
                yield "recherche/certifications";
            }
            case "formation" -> {
                model.addAttribute(RECHERCHE, coreRechercheService.rechercheFormations(critere));
                yield "recherche/formations";
            }
            default -> {
                model.addAttribute(RECHERCHE, coreRechercheService.rechercheEtablissements(critere));
                yield "recherche/etablissements";
            }
        };
    }
}
