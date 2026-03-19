package com.guillaumegasnier.education.shell.datasets.referentiels;

import com.guillaumegasnier.education.core.enums.OuiNon;
import com.guillaumegasnier.education.core.enums.TransitionEcologique;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


/**
 * Fichier : unix_referentiel_code_rome_v460_utf8.csv
 */
@Getter
@Setter
@Slf4j
public class RomeDataset extends AbtractRomeDataset {

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
    private String transitionEco;
    
    /**
     * Indication de si le métier est associé à la transition numérique
     */
    @CsvBindByName(column = "transition_num")
    private OuiNon transitionNum;

    /**
     * Indication de si le métier est associé à la transition démographique
     */
    @CsvBindByName(column = "transition_demo")
    private OuiNon transitionDemo;

    /**
     * Indication de si le métier est un métier réglementé
     */
    @CsvBindByName(column = "emploi_reglemente")
    private OuiNon emploiReglemente;

    /**
     * Indication de si le métier est un métier cadre
     */
    @CsvBindByName(column = "emploi_cadre")
    private OuiNon emploiCadre;

    /**
     * Identifiant technique de la fiche du métier
     */
    @CsvBindByName(column = "code_fiche_metier")
    private int codeFicheMetier;

    /**
     * Libellé du métier
     */
    @CsvBindByName(column = "libelle_rome")
    private String nom;

    /**
     * Identifiant fonctionnel du métier dont peut dériver/hériter le métier
     */
    @CsvBindByName(column = "code_rome_parent")
    private String codeRomeParent;

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
