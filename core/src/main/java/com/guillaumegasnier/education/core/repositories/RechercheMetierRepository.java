package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.recherche.RechercheMetierEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RechercheMetierRepository extends ElasticsearchRepository<RechercheMetierEntity, String> {
}
