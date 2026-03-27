package com.guillaumegasnier.education.web.api.impl;

import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.dto.recherche.RechercheEtablissementDTO;
import com.guillaumegasnier.education.core.services.CoreRechercheService;
import com.guillaumegasnier.education.web.api.ApiRechercheController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/recherche")
public class ApiRechercheImpl implements ApiRechercheController {

    private final CoreRechercheService coreRechercheService;

    @Autowired
    public ApiRechercheImpl(CoreRechercheService coreRechercheService) {
        this.coreRechercheService = coreRechercheService;
    }

    @Override
    public ResponseEntity<RechercheEtablissementDTO> getResultatRecherche(MultiValueMap<String, String> facettes) {
        RechercheCriteria critere = new RechercheCriteria(facettes);
        critere.setType("etablissement");
        return ResponseEntity.ok(coreRechercheService.rechercheEtablissements(critere));
    }

}
