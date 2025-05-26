package com.guillaumegasnier.education.annuaire.api;

import com.guillaumegasnier.education.annuaire.dto.EtablissementDto;
import com.guillaumegasnier.education.annuaire.dto.EtablissementRequestDto;
import com.guillaumegasnier.education.annuaire.dto.IPSDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IApiEtablissementController {

    @Operation(
            summary = "Recherche d'un établissement",
            tags = {"Etablissement"}
    )
    ResponseEntity<Page<EtablissementDto>> serachEtablissement(@RequestParam int page, @RequestParam int size);

    @Operation(
            summary = "Obtenir un établissement par son UAI",
            tags = {"Etablissement"}
    )
    ResponseEntity<EtablissementDto> getEtablissementByUai(@PathVariable String uai);

    @Operation(
            summary = "Création d'un établissement",
            tags = {"Etablissement"}
    )
    ResponseEntity<EtablissementDto> createEtablissement(@RequestBody EtablissementRequestDto etablissement);

    @Operation(
            summary = "Mise à jour d'un établissement",
            tags = {"Etablissement"}
    )
    ResponseEntity<EtablissementDto> updateEtablissement(@PathVariable String uai, @RequestBody EtablissementRequestDto etablissement);

    @Operation(
            summary = "Ajout ou mise à jour d'un IPS",
            tags = {"Etablissement", "IPS"}
    )
    ResponseEntity<IPSDto> createOrUpdateIndice(@PathVariable String uai, IPSDto ips);

    @Operation(
            summary = "Liste des IPS d'un établissement",
            tags = {"Etablissement", "IPS"}
    )
    ResponseEntity<List<IPSDto>> getEtablissementIPS(@PathVariable String uai);
}
