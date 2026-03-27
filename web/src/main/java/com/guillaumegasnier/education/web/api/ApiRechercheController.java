package com.guillaumegasnier.education.web.api;

import com.guillaumegasnier.education.core.dto.recherche.RechercheEtablissementDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

public interface ApiRechercheController {

    @Operation(
            summary = "Résultats de la recherche des établissements",
            tags = {"Recherche"}
    )
    @GetMapping("/etablissements")
    default ResponseEntity<RechercheEtablissementDTO> getResultatRecherche(@RequestParam MultiValueMap<String, String> facettes) {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

}
