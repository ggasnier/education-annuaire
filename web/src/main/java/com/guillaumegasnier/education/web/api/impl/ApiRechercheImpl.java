package com.guillaumegasnier.education.web.api.impl;

import com.guillaumegasnier.education.web.api.ApiRechercheController;
import com.guillaumegasnier.education.web.dto.FacetteRechercheDto;
import com.guillaumegasnier.education.web.dto.ResultatRechercheDto;
import com.guillaumegasnier.education.web.services.RechercheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/recherche")
public class ApiRechercheImpl implements ApiRechercheController {

    private final RechercheService rechercheService;

    @Autowired
    public ApiRechercheImpl(RechercheService rechercheService) {
        this.rechercheService = rechercheService;
    }

    @Override
    public ResponseEntity<ResultatRechercheDto> getResultatRecherche(@RequestParam MultiValueMap<String, String> facettes) {
        return ResponseEntity.ok(rechercheService.recherche(facettes));
    }

    @Override
    public ResponseEntity<List<FacetteRechercheDto>> getFacetteRecherche(@RequestParam MultiValueMap<String, String> facettes) throws IOException {
        return ResponseEntity.ok(rechercheService.facette(facettes));
    }
}
