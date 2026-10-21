package com.guillaumegasnier.education.web.controllers;

import com.guillaumegasnier.education.web.config.AppProperties;
import com.guillaumegasnier.education.web.dto.etablissements.CommuneWithEtablissementsDTO;
import com.guillaumegasnier.education.web.dto.etablissements.NatureWithEtablissementsDTO;
import com.guillaumegasnier.education.web.dto.territoires.AcademieWithDepartementsDTO;
import com.guillaumegasnier.education.web.dto.territoires.CommuneDTO;
import com.guillaumegasnier.education.web.dto.territoires.DepartementDTO;
import com.guillaumegasnier.education.web.exceptions.CommuneNotFoundException;
import com.guillaumegasnier.education.web.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController extends WebController {

    private final WebEtablissementService webEtablissementService;
    private final WebFormationService webFormationService;
    private final WebRechercheService webRechercheService;
    private final WebReferentielService webReferentielService;
    private final WebTerritoireService webTerritoireService;
    private final AppProperties appProperties;

    @GetMapping("")
    public String getHome(Model model) {

        model.addAttribute(TITLE, appProperties.getTitle());
        model.addAttribute("nbrEtabliseements", webEtablissementService.getNbrEtablissements());
        model.addAttribute("nbrFormations", webFormationService.getNbrFormations());
        model.addAttribute("nbrCertifications", webReferentielService.getNbrCertifications());
        model.addAttribute("nbrCompetences", webReferentielService.getNbrCompetences());
        model.addAttribute("nbrMetiers", webReferentielService.getNbrMetiers());

        return "home/index.html";
    }

    @GetMapping("/academies")
    public String getAcademies(Model model) {

        model.addAttribute(TITLE, "Academies");
//        List<DepartementEntity> departementEntityList = coreTerritoireService.getDepartements();
//        List<AcademieWithDepartementsDTO> academies = webReferenceMapper.groupByAcademie(departementEntityList);
        List<AcademieWithDepartementsDTO> academies = webTerritoireService.getAcademiesWithDepartements();

        model.addAttribute("academies", academies);

        return "academies.html";
    }

    @GetMapping("/communes/{code}")
    public String getCommune(@PathVariable String code, Model model) {
        Optional<CommuneDTO> communeOptional = webTerritoireService.findCommune(code);

        if (communeOptional.isPresent()) {
            var commune = communeOptional.get();
            List<NatureWithEtablissementsDTO> natures = webEtablissementService.getNatureWithEtablissementsList(commune.code());
            model.addAttribute(TITLE, commune.nom());
            model.addAttribute("natures", natures);
        } else {
            throw new CommuneNotFoundException(code);
        }

        return "references/commune.html";
    }

    @GetMapping("/departements/{code}")
    public String getDepartement(@PathVariable String code, Model model) {

        Optional<DepartementDTO> departementOptional = webTerritoireService.findDepartement(code);

        if (departementOptional.isPresent()) {
            var departement = departementOptional.get();
            model.addAttribute(TITLE, departement.nom());
            model.addAttribute("departement", departement);

            // TODO à revoir, on ne devrait pas faire appel à un service pour récupérer les communes avec établissements, mais plutôt récupérer les communes et ensuite les établissements par commune
            List<CommuneWithEtablissementsDTO> communes = webTerritoireService.getCommuneWithEtablissementsDTO(departement.code());
            model.addAttribute("communes", communes);

//        Optional<DepartementEntity> departementEntityOptional = coreTerritoireService.findDepartement(code);
//
//        if (departementEntityOptional.isPresent()) {
//            var entity = departementEntityOptional.get();
//
//            // Liste des communes
//
//            // Liste des établissements
//            List<EtablissementEntity> etablissementEntityList = coreEtablissementService.findEtablissementListByDepartement(code);
//
//            List<CommuneWithEtablissementsDto> communes = webEtablissementMapper.groupByCommune(etablissementEntityList);
//

            //  model.addAttribute(TITLE, entity.getNom());
            //model.addAttribute("communes", communes);
        }
        // TODO exception si le département n'existe pas

        return "departement.html";
    }

    @GetMapping("/mentions-legales")
    public String mentions(Model model) {
        model.addAttribute(TITLE, "Mentions légales");
        return "pages/mentions.html";
    }

    @GetMapping("/a-propos")
    public String apropos(Model model) {
        model.addAttribute(TITLE, "A propos");
        return "pages/apropos.html";
    }
}
