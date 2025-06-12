package com.guillaumegasnier.education.annuaire.api;

import com.guillaumegasnier.education.annuaire.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

public interface IApiReferenceController {

    @Operation(
            summary = "Création d'une commune",
            tags = {"Reference"}
    )
    @PostMapping("/communes")
    ResponseEntity<CommuneDto> createCommune(@RequestBody CommuneRequestDto request);

    @Operation(
            summary = "Liste des communes",
            tags = {"Reference"}
    )
    @GetMapping("/communes")
    default ResponseEntity<Page<CommuneDto>> getCommunes(@RequestParam int page) {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Détails d'une commune",
            tags = {"Reference"}
    )
    @GetMapping("/communes/{code}")
    default ResponseEntity<CommuneDto> getCommune(@PathVariable(name = "code") String code) {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Liste des régions",
            tags = {"Reference"}
    )
    @GetMapping("/regions")
    default ResponseEntity<List<RegionDto>> getRegions() {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Détails une région",
            tags = {"Reference"}
    )
    @GetMapping("/regions/{code}")
    default ResponseEntity<RegionDto> getRegion(@PathVariable(name = "code") String code) {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Liste des natures d'établissement",
            tags = {"Reference"}
    )
    @GetMapping("/natures")
    default ResponseEntity<List<NatureDto>> getNatures() {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Liste des académies",
            tags = {"Reference"}
    )
    @GetMapping("/academies")
    default ResponseEntity<List<AcademieDto>> getAcademies() {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Liste des départements",
            tags = {"Reference"}
    )
    @GetMapping("/departements")
    default ResponseEntity<List<DepartementDto>> getDepartements() {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

}
