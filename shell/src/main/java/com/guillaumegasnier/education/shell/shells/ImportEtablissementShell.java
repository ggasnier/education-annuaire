package com.guillaumegasnier.education.shell.shells;

import com.guillaumegasnier.education.core.validations.etablissements.Effectifs;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.guillaumegasnier.education.shell.datasets.Dataset;

public interface ImportEtablissementShell {

    void importEtablissements();

    void importEtablissementsEsr();

    void importEtablissementsEnOuverts();

    void importEtablissementsEnFermes();

    void importEtablissementsCarif();

    void importEtablissementsOnisep();

    void importEtablissementsMasa();

    void importJpo();

    void importSports();

    void importLangues();

    void importSpecialites();

    void importDispositifs();

    void importEuroscol();

    <T extends Effectifs & Metadata & Dataset> void importEffectifs();

    void importIpsColleges();

    void importIvaColleges();

    void importEtablissementsDetails();

    void importEtablissementsMetadatas();
}
