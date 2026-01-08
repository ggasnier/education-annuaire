package com.guillaumegasnier.education.shell.shells;

import com.guillaumegasnier.education.core.validations.Effectifs;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.guillaumegasnier.education.shell.datasets.Dataset;

public interface ImportEtablissementShell {

    void importEtablissements();

    void importEtablissementsEsr();

    void importEtablissementsEnOuverts();

    void importEtablissementsEnFermes();

    void importEtablissementsCarif();

    void importEtablissementsOnisepSup();

    void importEtablissementsMasa();

    void importSports();

    void importLangues();

    void importSpecialites();

    void importDispositifs();

    void importEuroscol();

    <T extends Effectifs & Metadata & Dataset> void importEffectifs();

    void importIpsColleges();

    void importIvaColleges();

    void importEtablissementsDetails();
}
