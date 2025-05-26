package com.guillaumegasnier.education.annuaire.api;

import com.guillaumegasnier.education.annuaire.dto.EtablissementDto;
import com.guillaumegasnier.education.annuaire.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.annuaire.dto.IPSDto;
import com.guillaumegasnier.education.annuaire.services.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<EtablissementDto> createEtablissement(EtablissementRequestDto etablissement) {

        Optional<EtablissementDto> result = etablissementService.createEtablissement(etablissement);
        return result.map(etablissementDto -> ResponseEntity.status(HttpStatus.CREATED).body(etablissementDto)).orElseGet(() -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErreurDto("Failed to create établissement")));
    }

    @GetMapping("")
    @Override
    public ResponseEntity<Page<EtablissementDto>> serachEtablissement(int page, int size) {
        return null;
    }

    @GetMapping("/{uai}")
    @Override
    public ResponseEntity<EtablissementDto> getEtablissementByUai(@PathVariable String uai) {
        return null;
    }

    @PatchMapping("/{uai}")
    @Override
    public ResponseEntity<EtablissementDto> updateEtablissement(@PathVariable String uai, EtablissementRequestDto etablissement) {
        return null;
    }

    @PostMapping("/{uai}/ips")
    @Override
    public ResponseEntity<IPSDto> createOrUpdateIndice(@PathVariable String uai, @RequestBody IPSDto ips) {
        return null;
    }

    @GetMapping("/{uai}/ips")
    @Override
    public ResponseEntity<List<IPSDto>> getEtablissementIPS(@PathVariable String uai) {
        return null;
    }
}
