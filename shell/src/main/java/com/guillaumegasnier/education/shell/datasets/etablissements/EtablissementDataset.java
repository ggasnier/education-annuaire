package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.enums.EtatEtablissement;
import com.guillaumegasnier.education.shell.datasets.Dataset;

import java.time.LocalDate;
import java.util.List;


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

}
