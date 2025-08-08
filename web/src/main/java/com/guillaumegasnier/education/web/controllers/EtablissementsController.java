package com.guillaumegasnier.education.web.controllers;

import com.guillaumegasnier.education.core.services.EtablissementService;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/etablissements")
public class EtablissementsController {

    private final EtablissementService etablissementService;

    @Autowired
    public EtablissementsController(EtablissementService etablissementService) {
        this.etablissementService = etablissementService;
    }


    @GetMapping("/{uai")
    public String getEtablissement(@PathVariable String uai, Model model) {

        EtablissementDto etablissementDto = etablissementService.getEtablissementByUai(uai);

        return "etablissements/details";
    }
}
