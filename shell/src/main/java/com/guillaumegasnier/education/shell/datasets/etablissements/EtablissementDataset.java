package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.Secteur;
import com.guillaumegasnier.education.core.validations.etablissements.Effectifs;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.guillaumegasnier.education.shell.datasets.Dataset;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public interface EtablissementDataset extends Cloneable, Dataset {

    EtablissementDataset cloneWithUai(String uai);

    EtablissementDataset cloneWithSiret(String siret);

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

    default Secteur getSecteur() {
        return null;
    }

    default List<JPODataset> getJPO() {
        return Collections.emptyList();
    }

    /**
     *
     * @return le Code DGER si renseigné
     */
    default String getMasaId() {
        return null;
    }

    default <T extends Effectifs & Metadata> List<T> getEffectifs() {
        return List.of();
    }
}
