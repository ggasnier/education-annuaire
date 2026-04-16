package com.guillaumegasnier.education.web.api.impl;

import com.guillaumegasnier.education.web.api.ApiEtablissementController;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.web.dto.etablissements.NatureDto;
import com.guillaumegasnier.education.web.services.WebEtablissementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/etablissements")
public class ApiEtablissementImpl implements ApiEtablissementController {

    private final WebEtablissementService webEtablissementService;

    public ApiEtablissementImpl(WebEtablissementService webEtablissementService) {
        this.webEtablissementService = webEtablissementService;
    }

    @Override
    public ResponseEntity<EtablissementDto> createEtablissement(@RequestBody EtablissementRequestDto dto) {
        try {
            return webEtablissementService.createEtablissement(dto)
                    .map(created -> ResponseEntity.status(201).body(created))
                    .orElse(ResponseEntity.status(BAD_GATEWAY).build());
        } catch (RuntimeException e) {
            log.error("Erreur lors de la création de l'établissement {}", dto.getUai(), e);
            throw e;
        }
    }

    @Override
    public ResponseEntity<EtablissementDto> updateEtablissement(@RequestBody EtablissementRequestDto dto) {
        try {
            return webEtablissementService.updateEtablissement(dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(NOT_FOUND).build());
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour de l'établissement {}", dto.getUai(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<NatureDto>> getNatureList() {
        return ResponseEntity.ok().body(webEtablissementService.getNatureList());
    }
}
