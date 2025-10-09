package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.domains.recherche.DocumentEntity;
import com.guillaumegasnier.education.core.repositories.DocumentRepository;
import com.guillaumegasnier.education.core.services.CoreElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreElasticServiceImpl implements CoreElasticService {

    private static final int BATCH_SIZE = 1000;
    private final DocumentRepository documentRepository;

    @Autowired
    public CoreElasticServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void saveDocuments(@NonNull List<DocumentEntity> entities) {

        for (int i = 0; i < entities.size(); i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, entities.size());
            List<DocumentEntity> batch = entities.subList(i, end);
            documentRepository.saveAll(batch);
        }

    }
}
