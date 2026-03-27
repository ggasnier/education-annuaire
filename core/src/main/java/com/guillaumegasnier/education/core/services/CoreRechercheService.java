package com.guillaumegasnier.education.core.services;

import com.guillaumegasnier.education.core.domains.recherche.RechercheEtablissementEntity;
import com.guillaumegasnier.education.core.domains.recherche.RechercheFormationEntity;
import com.guillaumegasnier.education.core.domains.recherche.RechercheMetierEntity;
import com.guillaumegasnier.education.core.dto.RechercheCriteria;
import com.guillaumegasnier.education.core.dto.recherche.*;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CoreRechercheService {

    void saveEtablissements(@NonNull List<RechercheEtablissementEntity> entities);

    void saveMetiers(@NonNull List<RechercheMetierEntity> entities);

    RechercheMetierDTO rechercheMetiers(@NonNull RechercheCriteria rechercheCriteria);

    RechercheEtablissementDTO rechercheEtablissements(@NonNull RechercheCriteria rechercheCriteria);

    RechercheCompetenceDTO rechercheCompetences(@NonNull RechercheCriteria rechercheCriteria);

    RechercheCertificationDTO rechercheCertifications(@NonNull RechercheCriteria rechercheCriteria);

    RechercheFormationDTO rechercheFormations(@NonNull RechercheCriteria rechercheCriteria);

    void saveFormations(List<RechercheFormationEntity> entities);

    void deleteEtablissements(List<RechercheEtablissementEntity> entities);
}
