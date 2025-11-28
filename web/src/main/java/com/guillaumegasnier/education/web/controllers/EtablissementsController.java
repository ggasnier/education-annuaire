package com.guillaumegasnier.education.web.controllers;

import com.guillaumegasnier.education.web.dto.etablissements.EtablissementDetailsDto;
import com.guillaumegasnier.education.web.services.WebEtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/etablissements")
public class EtablissementsController {

    private final WebEtablissementService webEtablissementService;

    @Autowired
    public EtablissementsController(WebEtablissementService webEtablissementService) {
        this.webEtablissementService = webEtablissementService;
    }

    @GetMapping("/{uai}")
    public String getEtablissement(@PathVariable String uai, Model model) {

        EtablissementDetailsDto details = webEtablissementService.findEtablissementDetailsDtoByUai(uai);

        if (details == null)
            return "etablissements/404";

        model.addAttribute("title", details.etablissement().getNom());
        model.addAttribute("etablissement", details.etablissement());
        model.addAttribute("options", details.options());
        model.addAttribute("specialites", details.specialites());
        model.addAttribute("langues", details.langues());
        model.addAttribute("sports", details.sports());
        model.addAttribute("metadatats", details.metadatas());

        return "etablissements/details";
    }
}
