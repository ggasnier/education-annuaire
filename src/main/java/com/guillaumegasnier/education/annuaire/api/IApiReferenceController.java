package com.guillaumegasnier.education.annuaire.api;

import com.guillaumegasnier.education.annuaire.dto.CommuneDto;
import com.guillaumegasnier.education.annuaire.dto.CommuneRequestDto;
import com.guillaumegasnier.education.annuaire.dto.NatureDto;
import com.guillaumegasnier.education.annuaire.dto.RegionDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

public interface IApiReferenceController {

    @Operation(
            tags = {"Reference"}
    )
    default ResponseEntity<CommuneDto> createCommune(@RequestBody @Valid CommuneRequestDto request) {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

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
    default ResponseEntity<CommuneDto> getCommune(@PathVariable(name = "commune_code") String codeCommune) {
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
    default ResponseEntity<RegionDto> getRegion(@PathVariable(name = "region_code") String codeRegion) {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Operation(
            summary = "Liste des natures d'établissement",
            tags = {"Reference"}
    )
    default ResponseEntity<List<NatureDto>> getNatures() {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }
}
