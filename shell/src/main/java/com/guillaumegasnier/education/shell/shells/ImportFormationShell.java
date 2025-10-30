package com.guillaumegasnier.education.shell.shells;

public interface ImportFormationShell {

    String importOrganismesFormations();

    String importRncp();

    String importRs();

    /**
     * Formations disponibles sur mon compte formation
     *
     * @return Résultat de l'import
     */
    String importFormationsCpf();

    /**
     * Formations disponibles sur l'espace Carif-Oref
     *
     * @return Résultat de l'import
     */
    String importFormationsCarif();

    /**
     * Formations de l'inseignement supérieur et de la recherche listées par l'Onisep
     *
     * @return Résultat de l'import
     */
    String importFormationsOnisepEsr();

    /**
     * Formations au format Lheo
     *
     * @return Résultat de l'import
     */
    String importFormationsOnisepLheo();

    /**
     * Formations sur parcoursup
     *
     * @return Résultat de l'import
     */
    String importFormationsParcoursup();


}
