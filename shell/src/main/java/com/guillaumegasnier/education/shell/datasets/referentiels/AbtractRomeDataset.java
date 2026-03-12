package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.core.enums.OuiNon;
import com.guillaumegasnier.education.core.enums.TransitionEcologique;
import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public abstract class AbtractRomeDataset implements Dataset {

    /**
     * Identifiant fonctionnel du métier
     */
    @CsvBindByName(column = "code_rome")
    protected String codeRome;

    @CsvBindByName(column = "code_ogr")
    protected int codeOgr;

    /**
     * Indication du niveau d’association à la transition écologique du métier
     * <p>
     * Valeurs possibles :
     * <ul>
     *     <li>Emploi stratégique pour la Transition écologique</li>
     *     <li>Emploi Vert</li>
     *     <li>Emploi Blanc</li>
     *     <li>Emploi Brun</li>
     * </ul>
     */
    @CsvBindByName(column = "transition_eco")
    protected String transitionEco;

    /**
     * Indication de si le métier est associé à la transition numérique
     */
    @CsvBindByName(column = "transition_num")
    protected OuiNon transitionNum;

    /**
     * Indication de si le métier est associé à la transition démographique
     */
    @CsvBindByName(column = "transition_demo")
    protected OuiNon transitionDemo;

    /**
     * Indication de si le métier est un métier réglementé
     */
    @CsvBindByName(column = "emploi_reglemente")
    protected OuiNon emploiReglemente;

    /**
     * Indication de si le métier est un métier cadre
     */
    @CsvBindByName(column = "emploi_cadre")
    protected OuiNon emploiCadre;

    public TransitionEcologique getTransitionEco() {
        return switch (transitionEco) {
            case "Emploi stratégique pour la Transition écologique" -> TransitionEcologique.S;
            case "Emploi Vert" -> TransitionEcologique.G;
            case "Emploi Blanc" -> TransitionEcologique.W;
            case "Emploi Brun" -> TransitionEcologique.B;
            default -> null;
        };
    }
}
