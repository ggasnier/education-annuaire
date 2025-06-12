package com.guillaumegasnier.education.annuaire.api;

import com.guillaumegasnier.education.annuaire.dto.*;
import com.guillaumegasnier.education.annuaire.validations.ValidUai;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IApiEtablissementController {

    @Operation(
            summary = "Recherche d'un établissement",
            tags = {"Etablissement"}
    )
    @GetMapping("")
    ResponseEntity<Page<EtablissementDto>> searchEtablissement(@RequestParam(required = false, defaultValue = "0") int page);

    @Operation(
            summary = "Obtenir un établissement par son UAI",
            tags = {"Etablissement"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Etablissement trouvé",
                            content = @Content(schema = @Schema(implementation = EtablissementDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "UAI invalide",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Etablissement non trouvé",
                            content = @Content()
                    )
            }
    )
    @GetMapping("/{uai}")
    ResponseEntity<EtablissementDto> getEtablissementByUai(@PathVariable @ValidUai String uai);

    @Operation(
            summary = "Création d'un établissement",
            tags = {"Etablissement"}
    )
    @PostMapping("")
    ResponseEntity<EtablissementDto> createEtablissement(@RequestBody @Valid EtablissementRequestDto request);

    @Operation(
            summary = "Mise à jour d'un établissement",
            tags = {"Etablissement"}
    )
    @PatchMapping("/{uai}")
    ResponseEntity<EtablissementDto> updateEtablissement(@PathVariable @ValidUai String uai, @RequestBody @Valid EtablissementRequestDto request);

    @Operation(
            summary = "Ajout ou mise à jour d'un IPS",
            tags = {"Etablissement"}
    )
    @PostMapping("/{uai}/ips")
    ResponseEntity<IPSDto> createOrUpdateIndice(@PathVariable @ValidUai String uai, @RequestBody @Valid IPSRequestDto request);

    @Operation(
            summary = "Liste des IPS d'un établissement",
            tags = {"Etablissement"}
    )
    @GetMapping("/{uai}/ips")
    ResponseEntity<List<IPSDto>> getEtablissementIPS(@PathVariable @ValidUai String uai);
}
