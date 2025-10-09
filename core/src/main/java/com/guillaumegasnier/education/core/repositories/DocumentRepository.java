package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.recherche.DocumentEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentRepository extends ElasticsearchRepository<DocumentEntity, UUID> {
}