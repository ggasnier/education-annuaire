package com.guillaumegasnier.education.core.services;

import com.guillaumegasnier.education.core.domains.recherche.DocumentEntity;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CoreElasticService {

    void saveDocuments(@NonNull List<DocumentEntity> entities);
}
