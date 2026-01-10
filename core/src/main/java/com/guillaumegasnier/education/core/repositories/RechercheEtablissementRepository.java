package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RechercheEtablissementRepository
        extends ElasticsearchRepository<RechercheEtablissementEntity, String> {
}
