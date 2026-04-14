package com.guillaumegasnier.education.web.api.impl;

import com.guillaumegasnier.education.web.api.ApiTerritoireController;
import com.guillaumegasnier.education.web.dto.CommuneDto;
import com.guillaumegasnier.education.web.dto.CommuneRequestDto;
import com.guillaumegasnier.education.web.dto.territoires.PaysDTO;
import com.guillaumegasnier.education.web.services.WebTerritoireService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/territoires")
public class ApiTerritoireImpl implements ApiTerritoireController {

    private final WebTerritoireService webTerritoireService;

    public ApiTerritoireImpl(WebTerritoireService webTerritoireService) {
        this.webTerritoireService = webTerritoireService;
    }

    @Override
    public ResponseEntity<List<PaysDTO>> getPaysList() {
        return ResponseEntity.ok(webTerritoireService.getPaysList());
    }

    @Override
    public ResponseEntity<CommuneDto> createCommune(CommuneRequestDto communeRequestDto) {
        try {
            CommuneDto created = webTerritoireService.createCommune(communeRequestDto);
            return ResponseEntity.status(201).body(created);
        } catch (IllegalStateException e) {
            log.warn("Conflit lors de la création de la commune : {}", e.getMessage());
            return ResponseEntity.status(409).build();
        } catch (Exception e) {
            log.error("Erreur lors de la création de la commune", e);
            return ResponseEntity.status(500).build();
        }
    }
}

