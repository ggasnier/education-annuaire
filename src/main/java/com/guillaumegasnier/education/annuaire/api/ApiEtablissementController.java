package com.guillaumegasnier.education.annuaire.api;

import com.guillaumegasnier.education.annuaire.dto.EtablissementDto;
import com.guillaumegasnier.education.annuaire.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.annuaire.dto.IPSDto;
import com.guillaumegasnier.education.annuaire.dto.IPSRequestDto;
import com.guillaumegasnier.education.annuaire.services.EtablissementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/etablissements")
public class ApiEtablissementController implements IApiEtablissementController {

    private final EtablissementService etablissementService;

    @Autowired
    public ApiEtablissementController(EtablissementService etablissementService) {
        this.etablissementService = etablissementService;
    }

    @PostMapping("")
    @Override
    public ResponseEntity<EtablissementDto> createEtablissement(@RequestBody @Valid EtablissementRequestDto etablissement) {
        return etablissementService.createEtablissement(etablissement)
                .map(etablissementDto -> ResponseEntity.status(HttpStatus.CREATED).body(etablissementDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
    }

    @GetMapping("")
    @Override
    public ResponseEntity<Page<EtablissementDto>> searchEtablissement(int page) {
        return ResponseEntity.status(HttpStatus.OK).body(etablissementService.searchEtablissement(page));
    }

    @GetMapping("/{uai}")
    @Override
    public ResponseEntity<EtablissementDto> getEtablissementByUai(@PathVariable String uai) {
        return etablissementService.getEtablissement(uai)
                .map(etablissementDto -> ResponseEntity.status(HttpStatus.OK).body(etablissementDto))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping("/{uai}")
    @Override
    public ResponseEntity<EtablissementDto> updateEtablissement(@PathVariable String uai, EtablissementRequestDto etablissement) {
        return etablissementService.updateEtablissement(etablissement)
                .map(etablissementDto -> ResponseEntity.status(HttpStatus.OK).body(etablissementDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
    }

    @PostMapping("/{uai}/ips")
    @Override
    public ResponseEntity<IPSDto> createOrUpdateIndice(@PathVariable String uai, @RequestBody IPSRequestDto ips) {
        return etablissementService.createOrUpdateIndice(uai, ips)
                .map(ipsDto -> ResponseEntity.status(HttpStatus.OK).body(ipsDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
    }

    @GetMapping("/{uai}/ips")
    @Override
    public ResponseEntity<List<IPSDto>> getEtablissementIPS(@PathVariable String uai) {
        return ResponseEntity.status(HttpStatus.OK).body(etablissementService.getEtablissementIPS(uai));
    }
}
