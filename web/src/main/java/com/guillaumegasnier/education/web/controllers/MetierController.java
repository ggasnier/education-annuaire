package com.guillaumegasnier.education.web.controllers;

import com.guillaumegasnier.education.web.dto.referentiels.MetierDetailsDto;
import com.guillaumegasnier.education.web.services.WebReferentielService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/metiers")
public class MetierController {

    private final WebReferentielService webReferentielService;

    public MetierController(WebReferentielService webReferentielService) {
        this.webReferentielService = webReferentielService;
    }

    @GetMapping("/{code}")
    public String getMetier(@PathVariable String code, Model model) {

        Optional<MetierDetailsDto> details = webReferentielService.findMetier(code);

        if (details.isEmpty())
            return "etablissements/404";

        model.addAttribute("metier", details.get());

        return "referentiels/metier";
    }

}
