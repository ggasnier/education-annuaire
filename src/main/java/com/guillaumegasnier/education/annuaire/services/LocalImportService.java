package com.guillaumegasnier.education.annuaire.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Deprecated
@Slf4j
@Service
@Profile("local")
public class LocalImportService {

//    private final EtablissementService etablissementService;
//
//    @Autowired
//    public LocalImportService(EtablissementService etablissementService) {
//        this.etablissementService = etablissementService;
//    }
//
//    @Override
//    public String importEnEtablissements() {
//        return etablissementService.importEnEtablissements(SourcesDatasets.EN_ETABS_OUVERTS.getPath());
//    }
//
//    @Override
//    public String importEsrEtablissements() {
//        return etablissementService.importEsrEtablissements(SourcesDatasets.ESR_ETABS_OUVERTS.getPath());
//    }
//
//    @Override
//    public String importCarifEtablissements() {
//        return etablissementService.importCarifEtablissements(SourcesDatasets.CARIF_ETABS_OUVERTS.getPath());
//    }

//    /**
//     * @param data Liste de données
//     * @param <T>  Le type à traiter
//     */
//    public <T> void insertOrUpdate(@NonNull List<T> data) {
//        if (data.isEmpty()) return;
//
//        T firstElement = data.getFirst(); // On utilise le premier élément pour déterminer le type
//
//        switch (firstElement) {
//            case EtablissementEntity etablissement -> insertOrUpdateEtablissement((List<EtablissementEntity>) data);
////            case IndicePositionSociale indicePositionSociale -> insertOrUpdateIPS((List<IndicePositionSociale>) data);
//            default ->
//                    throw new IllegalArgumentException("Type non supporté : " + firstElement.getClass().getSimpleName());
//        }
//    }

}
