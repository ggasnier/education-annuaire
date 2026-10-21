package com.guillaumegasnier.education.web.api;

import com.guillaumegasnier.education.web.dto.territoires.CommuneDTO;
import com.guillaumegasnier.education.web.dto.territoires.CommuneRequestDTO;
import com.guillaumegasnier.education.web.dto.territoires.PaysDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

public interface ApiTerritoireController {

    @Operation(
            summary = "Liste des pays",
            tags = {"Territoire"}
    )
    @GetMapping("/pays")
    default ResponseEntity<List<PaysDTO>> getPaysList() {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    // Commune par code

    // Créer une commune
    @Operation(
            summary = "Création d'une commune",
            tags = {"Territoire"}
    )
    @PostMapping("/communes")
    default ResponseEntity<CommuneDTO> createCommune(@RequestBody CommuneRequestDTO communeRequestDto) {
        return ResponseEntity.status(NOT_IMPLEMENTED).build();
    }

    // Mettre à jour commune
}
