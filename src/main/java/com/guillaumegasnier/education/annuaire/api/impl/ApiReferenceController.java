package com.guillaumegasnier.education.annuaire.api.impl;

import com.guillaumegasnier.education.annuaire.api.IApiReferenceController;
import com.guillaumegasnier.education.annuaire.dto.*;
import com.guillaumegasnier.education.annuaire.services.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/references")
public class ApiReferenceController implements IApiReferenceController {

    private final ReferenceService referenceService;

    @Autowired
    public ApiReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @Override
    public ResponseEntity<CommuneDto> createCommune(@RequestBody CommuneRequestDto request) {
        return referenceService.createCommune(request).map(
                communeDto -> ResponseEntity.status(HttpStatus.CREATED).body(communeDto)
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
    }

    @Override
    public ResponseEntity<CommuneDto> getCommune(@PathVariable String code) {
        return referenceService.getCommune(code)
                .map(communeDto -> ResponseEntity.status(HttpStatus.OK).body(communeDto))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<Page<CommuneDto>> getCommunes(@RequestParam(required = false, defaultValue = "0") int page) {
        return ResponseEntity.ok().body(referenceService.searchCommune(page));
    }

    @Override
    public ResponseEntity<List<NatureDto>> getNatures() {
        return ResponseEntity.ok().body(referenceService.getNatures());
    }

    @Override
    public ResponseEntity<List<AcademieDto>> getAcademies() {
        return ResponseEntity.ok().body(referenceService.getAcademies());
    }

    @Override
    public ResponseEntity<List<RegionDto>> getRegions() {
        return ResponseEntity.ok().body(referenceService.getRegions());
    }

    @Override
    public ResponseEntity<List<DepartementDto>> getDepartements() {
        return ResponseEntity.ok().body(referenceService.getDepartements());
    }
}
