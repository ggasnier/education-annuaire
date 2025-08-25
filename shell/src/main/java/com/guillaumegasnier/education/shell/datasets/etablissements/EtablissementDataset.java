package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.EtatEtablissement;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.shell.datasets.Dataset;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public interface EtablissementDataset extends Cloneable, Dataset {

    EtablissementDataset cloneWithUai(String uai);

    EtablissementDataset cloneWithSiret(String siret);

    String getUai();

    String getSiret();

    String getNom();

    String getAdresse();

    String getComplement();

    String getCodePostal();

    String getCodeCommune();

    default String getNomCommune() {
        return null;
    }

    String getCodePays();

    String getCodeNature();

    String getCodeContrat();

    EtatEtablissement getEtat();

    List<ContactEtablissementDataset> getContacts();

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
