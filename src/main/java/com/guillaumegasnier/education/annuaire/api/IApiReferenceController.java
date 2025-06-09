package com.guillaumegasnier.education.annuaire.api;

import com.guillaumegasnier.education.annuaire.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

public interface IApiReferenceController {

    ResponseEntity<CommuneDto> createCommune(@RequestBody CommuneRequestDto request);

    @Operation(
            summary = "Liste des communes",
            tags = {"Reference"}
    )
    default ResponseEntity<Page<CommuneDto>> getCommunes(@RequestParam int page) {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Obtenir une commune par son code",
            tags = {"Reference"}
    )
    default ResponseEntity<CommuneDto> getCommune(@PathVariable(name = "code") String codeCommune) {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Liste des régions",
            tags = {"Reference"}
    )
    default ResponseEntity<List<RegionDto>> getRegions() {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Obtenir une région par son code",
            tags = {"Reference"}
    )
    default ResponseEntity<RegionDto> getRegion(@PathVariable(name = "code") String code) {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Liste des natures d'établissement",
            tags = {"Reference"}
    )
    default ResponseEntity<List<NatureDto>> getNatures() {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Liste des académies",
            tags = {"Reference"}
    )
    default ResponseEntity<List<AcademieDto>> getAcademies() {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Liste des départements",
            tags = {"Reference"}
    )
    default ResponseEntity<List<DepartementDto>> getDepartements() {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

}
