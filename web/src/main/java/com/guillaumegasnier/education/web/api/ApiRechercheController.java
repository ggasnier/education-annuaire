package com.guillaumegasnier.education.web.api;

import com.guillaumegasnier.education.core.dto.RechercheFacetteDTO;
import com.guillaumegasnier.education.core.dto.RechercheResultatsDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

public interface ApiRechercheController {

    @Operation(
            summary = "Résultats de la recherche",
            tags = {"Recherche"}
    )
    @GetMapping("/resultats")
    default ResponseEntity<RechercheResultatsDTO> getResultatRecherche(@RequestParam MultiValueMap<String, String> facettes) {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Facettes de la recherche",
            tags = {"Recherche"}
    )
    @GetMapping("/facettes")
    default ResponseEntity<List<RechercheFacetteDTO>> getFacetteRecherche(@RequestParam MultiValueMap<String, String> facettes) throws IOException {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }
}
