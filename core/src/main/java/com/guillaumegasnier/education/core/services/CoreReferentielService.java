package com.guillaumegasnier.education.core.services;

import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;

import java.util.List;
import java.util.Optional;

public interface CoreReferentielService {

    void saveMetiers(List<MetierEntity> entities);

    Optional<MetierEntity> findMetier(String code);

    List<MetierEntity> findAll();
}
