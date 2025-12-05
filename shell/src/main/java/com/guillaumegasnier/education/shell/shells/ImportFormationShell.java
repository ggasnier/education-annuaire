package com.guillaumegasnier.education.shell.shells;

public interface ImportFormationShell {

    /**
     * Import de tous les organismes de formation
     */
    void importOrganismesFormations();

    /**
     * Formations disponibles sur mon compte formation
     */
    void importFormationsCpf();

    /**
     * Formations disponibles sur l'espace Carif-Oref
     *
     */
    void importFormationsCarif();

    /**
     * Formations de l'inseignement supérieur et de la recherche listées par l'Onisep
     *
     */
    void importFormationsOnisepEsr();

    /**
     * Formations au format Lheo
     *
     */
    void importFormationsOnisepLheo();

    /**
     * Formations sur parcoursup
     */
    void importFormationsParcoursup();


}
