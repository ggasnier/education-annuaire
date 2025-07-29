package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.etablissements.enums.EtatEtablissement;

import java.util.List;


public interface EtablissementDataset extends Cloneable {

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

}
