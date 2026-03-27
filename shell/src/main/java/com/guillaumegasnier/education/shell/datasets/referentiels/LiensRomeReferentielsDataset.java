package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 */
@Getter
@Setter
@ToString
public class LiensRomeReferentielsDataset {

    @CsvBindByName(column = "code_rome")
    private String codeRome;

    /**
     * <ul>
     *     <li>1: Code ROME et intitulé</li>
     *     <li>2: Emplois</li>
     *     <li>3: Définition</li>
     *     <li>4: Accès au métier</li>
     *     <li>5: Compétences</li>
     *     <li>6: Contextes de travai</li>
     *     <li>7: Secteurs d'activité</li>
     *     <li>8: Mobilité professionnelle</li>
     * </ul>
     */
    @CsvBindByName(column = "code_compo_bloc")
    private int codeCompoBloc;

    /**
     * Seulement pour le bloc 5 (Compétences)
     * <ul>
     *     <li>1: Savoir-faire</li>
     *     <li>2: Savoir-être professionels</li>
     *     <li>3: Savoirs</li>
     * </ul>
     */
    @CsvBindByName(column = "code_rubrique")
    private int codeRubrique;

    /**
     *
     */
    @CsvBindByName(column = "code_ogr")
    private int codeOgr;
}
