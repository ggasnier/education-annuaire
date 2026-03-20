package com.guillaumegasnier.education.core.services;

import com.guillaumegasnier.education.core.domains.referentiels.CertificationNationaleEntity;
import com.guillaumegasnier.education.core.domains.referentiels.CompetenceEntity;
import com.guillaumegasnier.education.core.domains.referentiels.MacroCompetenceEntity;
import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;

import java.util.List;
import java.util.Optional;

public interface CoreReferentielService {

    void saveMetiers(List<MetierEntity> entities);

    Optional<MetierEntity> findMetier(String code);

    List<MetierEntity> findAll();

    Optional<CompetenceEntity> findCompetence(int code);

    Optional<MacroCompetenceEntity> findMacroCompetence(String code);

    void saveCompetences(List<CompetenceEntity> entities);

    long getNbrMetiers();

    long getNbrCompetences();

    long getNbrCertifications();

    Optional<CertificationNationaleEntity> findCertification(String code);

    void saveCertifications(List<CertificationNationaleEntity> entities);
}
