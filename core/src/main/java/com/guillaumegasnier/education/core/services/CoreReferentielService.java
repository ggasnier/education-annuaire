package com.guillaumegasnier.education.core.services;

import com.guillaumegasnier.education.core.domains.referentiels.*;
import com.guillaumegasnier.education.core.enums.TypologieDiplome;
import org.jspecify.annotations.NonNull;

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

    Optional<CertificationNationaleEntity> findCertification(TypologieDiplome typologieDiplome, String nom, boolean actif);

    void saveSavoirs(List<MetierSavoirEntity> savoirs);

    Optional<MetierSavoirEntity> findSavoir(int codeOgr);

    void saveMetierSavoirLien(List<MetierSavoirLienEntity> liens);

    Optional<MetierSavoirLienEntity> findMetierSavoirLien(String code, int code1);

    List<MetierSavoirLienEntity> getMetierSavoirList(@NonNull String code);

    void saveMacroCompetences(@NonNull List<MacroCompetenceEntity> entities);

    void saveObjectifs(@NonNull List<ObjectifEntity> entities);

    Optional<ObjectifEntity> findObjectif(@NonNull String code);

    Optional<EnjeuEntity> findEnjeu(@NonNull String code);

    void saveEnjeux(@NonNull List<EnjeuEntity> entities);

    void saveDomaines(@NonNull List<DomaineEntity> entities);

    Optional<DomaineEntity> findDomaine(int code);

    Optional<MetierCompetenceEntity> findMetierCompetence(String code, Integer code1);

    void saveMetierCompetenceLien(List<MetierCompetenceEntity> liens);

    List<MetierCompetenceEntity> getMetierCompetenceList(String code);
}
