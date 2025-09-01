package com.guillaumegasnier.education.web.controllers;

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

        var etablissementDto = webEtablissementService.findEtablissementByUai(uai);

        if (etablissementDto.isPresent()) {
            model.addAttribute("etablissement", etablissementDto.get());
            model.addAttribute("options", webEtablissementService.getOptionListByUai(uai));
            model.addAttribute("langues", webEtablissementService.getLangueListByUai(uai));

            return "etablissements/details";
        } else {
            return "etablissements/404";
        }
    }
}
