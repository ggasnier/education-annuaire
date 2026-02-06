package com.guillaumegasnier.education.web.api.impl;

import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.dto.RechercheDTO;
import com.guillaumegasnier.education.core.services.CoreRechercheService;
import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.dto.RechercheDTO;
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

//    @Override
//    public ResponseEntity<RechercheResultatsDTO> getResultatRecherche(@RequestParam MultiValueMap<String, String> facettes) {
//        return ResponseEntity.ok(coreRechercheService.searchEtablissements(new RechercheCriteria(facettes)));
//    }
//
//    @Override
//    public ResponseEntity<List<RechercheFacetteDTO>> getFacetteRecherche(@RequestParam MultiValueMap<String, String> facettes) throws IOException {
//        return ResponseEntity.ok(coreRechercheService.searchEtablissementsFacettes(new RechercheCriteria(facettes)));
//    }

    @Override
    public ResponseEntity<RechercheDTO> getRecherche(MultiValueMap<String, String> facettes) {
        return ResponseEntity.ok(coreRechercheService.recherche(new RechercheCriteria(facettes)));
    }
}
