package com.guillaumegasnier.education.web.controllers;

import com.guillaumegasnier.education.core.enums.CoeurMetier;
import com.guillaumegasnier.education.web.dto.referentiels.CompetenceDTO;
import com.guillaumegasnier.education.web.dto.referentiels.MetierDTO;
import com.guillaumegasnier.education.web.dto.referentiels.MetierDetailsDTO;
import com.guillaumegasnier.education.web.dto.referentiels.SavoirDTO;
import com.guillaumegasnier.education.web.services.WebReferentielService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/metiers")
public class MetierController {

    private final WebReferentielService webReferentielService;

    @GetMapping("")
    public String index(Model model) {
        List<MetierDTO> metiers = webReferentielService.getAllMetiers();

        model.addAttribute("metiers", metiers);

        return "referentiels/metiers/index";
    }

    @GetMapping("/{code}")
    public String getMetier(@PathVariable String code, Model model) {

        Optional<MetierDetailsDTO> details = webReferentielService.findMetier(code);
        Map<String, List<SavoirDTO>> savoirs = webReferentielService.getMetierSavoirList(code);
        Map<CoeurMetier, Map<String, List<CompetenceDTO>>> competences = webReferentielService.getMetierCompetenceList(code);

        if (details.isEmpty())
            return "referentiels/metiers/404";

        model.addAttribute("metier", details.get());
        model.addAttribute("savoirs", savoirs);
        model.addAttribute("competences", competences);

        return "referentiels/metiers/details";
    }

}
