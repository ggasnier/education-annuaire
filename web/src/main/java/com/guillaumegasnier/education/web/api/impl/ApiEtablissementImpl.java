package com.guillaumegasnier.education.web.api.impl;


import com.guillaumegasnier.education.web.api.ApiEtablissementController;
import com.guillaumegasnier.education.web.dto.EtablissementDto;
import com.guillaumegasnier.education.web.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.web.services.WebEtablissementService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/etablissements")
public class ApiEtablissementImpl implements ApiEtablissementController {

    private final WebEtablissementService webEtablissementService;

    @Autowired
    public ApiEtablissementImpl(WebEtablissementService webEtablissementService) {
        this.webEtablissementService = webEtablissementService;
    }

    @Override
    public ResponseEntity<EtablissementDto> createEtablissement(@Valid @RequestBody EtablissementRequestDto etablissement) {
        log.info(etablissement.toString());
        return webEtablissementService.createEtablissement(etablissement)
                .map(etablissementDto -> ResponseEntity.status(HttpStatus.CREATED).body(etablissementDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
    }

    @Override
    public ResponseEntity<Page<EtablissementDto>> searchEtablissement(int page) {
//        return ResponseEntity.status(HttpStatus.OK).body(etablissementService.searchEtablissement(page));
        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
    }

    @Override
    public ResponseEntity<EtablissementDto> getEtablissementByUai(@PathVariable String uai) {
        return webEtablissementService.findEtablissementByUai(uai)
                .map(etablissementDto -> ResponseEntity.status(HttpStatus.OK).body(etablissementDto))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

//    @Override
//    public ResponseEntity<EtablissementDto> updateEtablissement(@PathVariable String uai, EtablissementRequestDto etablissement) {
//        return etablissementService.updateEtablissement(etablissement)
//                .map(etablissementDto -> ResponseEntity.status(HttpStatus.OK).body(etablissementDto))
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
//        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
//
//    }

//    @Override
//    public ResponseEntity<IPSDto> createOrUpdateIndice(@PathVariable String uai, @RequestBody IPSRequestDto ips) {
//        return etablissementService.createOrUpdateIndice(uai, ips)
//                .map(ipsDto -> ResponseEntity.status(HttpStatus.OK).body(ipsDto))
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
//        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
//
//    }

//    @Override
//    public ResponseEntity<List<IPSDto>> getEtablissementIPS(String uai) {
////        return ResponseEntity.status(HttpStatus.OK).body(etablissementService.getEtablissementIPS(uai));
//        return ResponseEntity.status(NOT_IMPLEMENTED).body(null);
//
//    }
}
