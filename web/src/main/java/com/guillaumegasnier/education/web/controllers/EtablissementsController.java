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

        model.addAttribute("title", details.getEtablissement().getNom());
        model.addAttribute("etablissement", details.getEtablissement());
        model.addAttribute("options", details.getOptions());
        model.addAttribute("specialites", details.getSpecialites());
        model.addAttribute("langues", details.getLangues());
        model.addAttribute("sports", details.getSports());
        model.addAttribute("metadatats", details.getMetadatas());

        return "etablissements/details";

//        var etablissementDto = webEtablissementService.findEtablissementByUai(uai);
//
//        if (etablissementDto.isPresent()) {
//            model.addAttribute("title", etablissementDto.get().getNom());
//
//            model.addAttribute("etablissement", etablissementDto.get());
//            model.addAttribute("options", webEtablissementService.getOptionListByUai(uai));
//            model.addAttribute("specialites", webEtablissementService.getSpecialiteListByUai(uai));
//            model.addAttribute("langues", webEtablissementService.getLangueListByUai(uai));
//            model.addAttribute("sports", webEtablissementService.getSportListByUai(uai));
//            model.addAttribute("indices", Collections.emptyList());
//
//            return "etablissements/details";
//        } else {
//
//        }
    }
}
