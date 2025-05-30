package com.guillaumegasnier.education.annuaire.api;

import com.guillaumegasnier.education.annuaire.dto.CommuneDto;
import com.guillaumegasnier.education.annuaire.dto.CommuneRequestDto;
import com.guillaumegasnier.education.annuaire.services.ReferenceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/references")
public class ApiReferenceController implements IApiReferenceController {

    private final ReferenceService referenceService;

    @Autowired
    public ApiReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @Operation(
            summary = "Création d'une commune",
            tags = {"Reference"}
    )
    @Override
    @PostMapping("/communes")
    public ResponseEntity<CommuneDto> createCommune(@RequestBody CommuneRequestDto request) {
        return referenceService.createCommune(request).map(
                communeDto -> ResponseEntity.status(HttpStatus.CREATED).body(communeDto)
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
    }
}
