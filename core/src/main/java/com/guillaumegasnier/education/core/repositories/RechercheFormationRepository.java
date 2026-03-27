package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.recherche.RechercheFormationEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RechercheFormationRepository extends ElasticsearchRepository<RechercheFormationEntity, Long> {
}
