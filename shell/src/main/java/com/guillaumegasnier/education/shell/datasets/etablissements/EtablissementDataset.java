package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.shell.datasets.Dataset;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public interface EtablissementDataset extends Cloneable, Dataset {

    EtablissementDataset cloneWithUai(String uai);

    EtablissementDataset cloneWithSiret(String siret);

    UUID getId();

    default String getUai() {
        return null;
    }

    default String getSiret() {
        return null;
    }

    default String getNumeroDeclarationActivite() {
        return null;
    }

    String getNom();

    String getAdresse();

    default String getComplement() {
        return null;
    }

    String getCodePostal();

    String getCodeCommune();

    default String getNomCommune() {
        return null;
    }

    default String getCodePays() {
        return null;
    }

    default String getCodeNature() {
        return null;
    }

    default String getEducationPrioritaire() {
        return null;
    }

    default String getCodeContrat() {
        return null;
    }

    default Boolean isActif() {
        return null;
    }

    default List<ContactEtablissementDataset> getContacts() {
        return Collections.emptyList();
    }

    default LocalDate getDateOuverture() {
        return null;
    }

    default LocalDate getDateFermeture() {
        return null;
    }

    default Set<OptionEtablissement> getOptions() {
        return Collections.emptySet();
    }

}
