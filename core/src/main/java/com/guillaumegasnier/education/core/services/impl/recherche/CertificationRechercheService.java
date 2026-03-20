package com.guillaumegasnier.education.core.services.impl.recherche;

import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.dto.recherche.RechercheCertificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CertificationRechercheService {

    // TODO : injecter RechercheCertificationRepository quand l'entité sera créée
    @SuppressWarnings("unused")
    private final ElasticsearchOperations elasticsearchOperations;

    public CertificationRechercheService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public RechercheCertificationDTO rechercheCertifications(@NonNull RechercheCriteria criteria) {
        // TODO : implémenter full-text + facettes comme EtablissementRechercheService
        return new RechercheCertificationDTO();
    }
}

