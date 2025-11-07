package com.guillaumegasnier.education.web.controllers;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import com.guillaumegasnier.education.core.domains.references.CommuneEntity;
import com.guillaumegasnier.education.core.domains.references.DepartementEntity;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreReferenceService;
import com.guillaumegasnier.education.web.dto.etablissements.CommuneWithEtablissementsDto;
import com.guillaumegasnier.education.web.dto.etablissements.NatureWithEtablissementsDto;
import com.guillaumegasnier.education.web.dto.references.AcademieWithDepartementsDto;
import com.guillaumegasnier.education.web.mappers.WebEtablissementMapper;
import com.guillaumegasnier.education.web.mappers.WebReferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {

    private final CoreReferenceService coreReferenceService;
    private final CoreEtablissementService coreEtablissementService;
    private final WebReferenceMapper webReferenceMapper;
    private final WebEtablissementMapper webEtablissementMapper;

    @Autowired
    public IndexController(CoreReferenceService coreReferenceService, CoreEtablissementService coreEtablissementService, WebReferenceMapper webReferenceMapper, WebEtablissementMapper webEtablissementMapper) {
        this.coreReferenceService = coreReferenceService;
        this.coreEtablissementService = coreEtablissementService;
        this.webReferenceMapper = webReferenceMapper;
        this.webEtablissementMapper = webEtablissementMapper;
    }

    @GetMapping("")
    public String getHome(Model model) {

        model.addAttribute("title", "TODO");


        return "home.html";
    }

    @GetMapping("/academies")
    public String getAcademies(Model model) {

        model.addAttribute("title", "Academies");

        List<DepartementEntity> departementEntityList = coreReferenceService.getDepartements();

        List<AcademieWithDepartementsDto> academies = webReferenceMapper.groupByAcademie(departementEntityList);

        model.addAttribute("academies", academies);

        return "academies.html";
    }


    @GetMapping("/communes/{code}")
    public String getCommune(@PathVariable String code, Model model) {
        Optional<CommuneEntity> communeEntityOptional = coreReferenceService.findCommune(code);

        if (communeEntityOptional.isPresent()) {
            var entity = communeEntityOptional.get();

            List<EtablissementEntity> etablissementEntityList = coreEtablissementService.findEtablissementListByCommune(entity.getCode());

            List<NatureWithEtablissementsDto> natures = webEtablissementMapper.groupbyNature(etablissementEntityList);

            model.addAttribute("title", entity.getNom());
            model.addAttribute("natures", natures);
        }

        return "commune.html";
    }

    @GetMapping("/departements/{code}")
    public String getDepartement(@PathVariable String code, Model model) {

        Optional<DepartementEntity> departementEntityOptional = coreReferenceService.findDepartement(code);

        if (departementEntityOptional.isPresent()) {
            var entity = departementEntityOptional.get();

            // Liste des communes

            // Liste des établissements
            List<EtablissementEntity> etablissementEntityList = coreEtablissementService.findEtablissementListByDepartement(code);

            List<CommuneWithEtablissementsDto> communes = webEtablissementMapper.groupByCommune(etablissementEntityList);


            model.addAttribute("title", entity.getNom());
            model.addAttribute("communes", communes);
        }

        return "departement.html";
    }
}
