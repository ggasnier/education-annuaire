package com.guillaumegasnier.education.web.api;

import com.guillaumegasnier.education.web.dto.ApiError;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.web.dto.etablissements.NatureDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

public interface ApiEtablissementController {

    @Operation(
            summary = "Liste des natures d'établissements",
            tags = {"Etablissement"}
    )
    @GetMapping("/natures")
    default ResponseEntity<List<NatureDto>> getNatureList() {
        return ResponseEntity.status(NOT_IMPLEMENTED).build();

    }

    @Operation(
            summary = "Création d'un établissement",
            description = "Création d'un nouvel établissement à partir de son code UAI (obligatoire) et des informations complémentaires fournies.",
            tags = {"Etablissement"},
            requestBody = @RequestBody(
                    description = "Données de l'établissement à créer. Le champ `uai` est obligatoire.",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EtablissementRequestDto.class)
                    )
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Établissement créé avec succès",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = EtablissementDto.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Données invalides (UAI mal formé, contraintes de validation non respectées)",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ApiError.class)
            )
    )
    @ApiResponse(
            responseCode = "409",
            description = "Un établissement avec ce code UAI existe déjà",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ApiError.class)
            )
    )
    @PostMapping("")
    default ResponseEntity<EtablissementDto> createEtablissement(@RequestBody EtablissementRequestDto dto) {
        return ResponseEntity.status(NOT_IMPLEMENTED).build();
    }

    @Operation(
            summary = "Mise à jour d'un établissement",
            tags = {"Etablissement"}
    )
    @PutMapping("")
    default ResponseEntity<EtablissementDto> updateEtablissement(@RequestBody EtablissementRequestDto dto) {
        return ResponseEntity.status(NOT_IMPLEMENTED).build();
    }
}
