package com.guillaumegasnier.education.shell.shells;

public interface ImportEtablissementShell {

    /**
     * Import de tous les établissements
     *
     * @return Message
     */
    String importEtablissementsAll();

    String importEtablissementsEsr();

    String importEtablissementsEnOuverts();

    String importEtablissementsEnFermes();

    String importEtablissementsCarif();

    String importEtablissementsOnisepSup();

    String importOrganismes();

    String importNatures();

    String importContrats();

    String importSectionsSportives();

    String importSectionsSportEtudes();

    String importSectionsInternationales();

    String importLangues();

    String importSpecialites();

    String importSectionsBinationales();

    String importIpsEcoles();

    String importIpsColleges();

    String importIpsLycees();

    String importEtablissementsRecherche();
}
