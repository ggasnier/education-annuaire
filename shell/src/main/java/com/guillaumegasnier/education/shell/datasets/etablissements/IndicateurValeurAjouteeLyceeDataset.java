package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.core.dto.ResultatFiliereDto;
import com.guillaumegasnier.education.core.validations.IndicateurValeurAjoutee;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class IndicateurValeurAjouteeLyceeDataset implements IndicateurValeurAjoutee, Metadata {

    @CsvBindByName(column = "Année")
    private Integer annee;

    @CsvBindByName(column = "UAI")
    private String uai;

    //Présents - Toutes séries

    //Taux de réussite - Toutes séries

    //Valeur ajoutée du taux de réussite - Toutes séries

    //Taux d'accès 2nde-bac

    //Valeur ajoutée du taux d'acces 2nde-bac

    //Taux de mentions - Toutes séries

    //Valeur ajoutée du taux de mentions - Toutes séries
    //    @CsvBindByName(column = "Nombre de mentions TB avec félicitations - G")
    //    @CsvBindByName(column = "Nombre de mentions TB sans félicitations - G")
    //    @CsvBindByName(column = "Nombre de mentions B - G")
    //    @CsvBindByName(column = "Nombre de mentions AB - G")
    //    @CsvBindByName(column = "Nombre de mentions TB avec félicitations - T")
    //    @CsvBindByName(column = "Nombre de mentions TB sans félicitations - T")
    //    @CsvBindByName(column = "Nombre de mentions B - T")
    //    @CsvBindByName(column = "Nombre de mentions AB - T")
    @CsvBindByName(column = "Effectif de seconde")
    private Integer effectifSeconde;
    @CsvBindByName(column = "Effectif de premiere")
    private Integer effectifPremiere;
    @CsvBindByName(column = "Effectif de terminale")
    private Integer effectifTerminale;
    @CsvBindByName(column = "Taux d'accès 1ere-bac")
    private Double tauxAcces1ereBac;
    @CsvBindByName(column = "Taux d'accès terminale-bac")
    private Double tauxAccesTerminaleBac;
    @CsvBindByName(column = "Valeur ajoutée du taux d'accès 1ere-bac")
    private Double valeurAjouteeTauxAcces1ereBac;
    @CsvBindByName(column = "Valeur ajoutée du taux d'accès terminale-bac")
    private Double valeurAjouteeTauxAccesTerminaleBac;
    @CsvBindByName(column = "Présents - L")
    private Integer presentsL;
    @CsvBindByName(column = "Présents - ES")
    private Integer presentsES;
    @CsvBindByName(column = "Présents - S")
    private Integer presentsS;
    @CsvBindByName(column = "Présents - Gnle")
    private Integer presentsGnle;
    @CsvBindByName(column = "Présents - STI2D")
    private Integer presentsSTI2D;
    @CsvBindByName(column = "Présents - STD2A")
    private Integer presentsSTD2A;
    @CsvBindByName(column = "Présents - STMG")
    private Integer presentsSTMG;
    @CsvBindByName(column = "Présents - STL")
    private Integer presentsSTL;
    @CsvBindByName(column = "Présents - ST2S")
    private Integer presentsST2S;
    @CsvBindByName(column = "Présents - S2TMD")
    private Integer presentsS2TMD;
    @CsvBindByName(column = "Présents - STHR")
    private Integer presentsSTHR;
    @CsvBindByName(column = "Taux de réussite - L")
    private Double tauxReussiteL;
    @CsvBindByName(column = "Taux de réussite - ES")
    private Double tauxReussiteES;
    @CsvBindByName(column = "Taux de réussite - S")
    private Double tauxReussiteS;
    @CsvBindByName(column = "Taux de réussite - Gnle")
    private Double tauxReussiteGnle;
    @CsvBindByName(column = "Taux de réussite - STI2D")
    private Double tauxReussiteSTI2D;
    @CsvBindByName(column = "Taux de réussite - STD2A")
    private Double tauxReussiteSTD2A;
    @CsvBindByName(column = "Taux de réussite - STMG")
    private Double tauxReussiteSTMG;
    @CsvBindByName(column = "Taux de réussite - STL")
    private Double tauxReussiteSTL;
    @CsvBindByName(column = "Taux de réussite - ST2S")
    private Double tauxReussiteST2S;
    @CsvBindByName(column = "Taux de réussite - S2TMD")
    private Double tauxReussiteS2TMD;
    @CsvBindByName(column = "Taux de réussite - STHR")
    private Double tauxReussiteSTHR;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - L")
    private Double valeurAjouteeTauxReussiteL;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - ES")
    private Double valeurAjouteeTauxReussiteES;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - S")
    private Double valeurAjouteeTauxReussiteS;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - Gnle")
    private Double valeurAjouteeTauxReussiteGnle;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - STI2D")
    private Double valeurAjouteeTauxReussiteSTI2D;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - STD2A")
    private Double valeurAjouteeTauxReussiteSTD2A;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - STMG")
    private Double valeurAjouteeTauxReussiteSTMG;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - STL")
    private Double valeurAjouteeTauxReussiteSTL;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - ST2S")
    private Double valeurAjouteeTauxReussiteST2S;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - S2TMD")
    private Double valeurAjouteeTauxReussiteS2TMD;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - STHR")
    private Double valeurAjouteeTauxReussiteSTHR;
    @CsvBindByName(column = "Taux de mentions - L")
    private Double tauxMentionsL;
    @CsvBindByName(column = "Taux de mentions - ES")
    private Double tauxMentionsES;
    @CsvBindByName(column = "Taux de mentions - S")
    private Double tauxMentionsS;
    @CsvBindByName(column = "Taux de mentions - Gnle")
    private Double tauxMentionsGnle;
    @CsvBindByName(column = "Taux de mentions - STI2D")
    private Double tauxMentionsSTI2D;
    @CsvBindByName(column = "Taux de mentions - STD2A")
    private Double tauxMentionsSTD2A;
    @CsvBindByName(column = "Taux de mentions - STMG")
    private Double tauxMentionsSTMG;
    @CsvBindByName(column = "Taux de mentions - STL")
    private Double tauxMentionsSTL;
    @CsvBindByName(column = "Taux de mentions - ST2S")
    private Double tauxMentionsST2S;
    @CsvBindByName(column = "Taux de mentions - S2TMD")
    private Double tauxMentionsS2TMD;
    @CsvBindByName(column = "Taux de mentions - STHR")
    private Double tauxMentionsSTHR;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - L")
    private Double valeurAjouteeTauxMentionsL;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - ES")
    private Double valeurAjouteeTauxMentionsES;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - S")
    private Double valeurAjouteeTauxMentionsS;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - Gnle")
    private Double valeurAjouteeTauxMentionsGnle;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - STI2D")
    private Double valeurAjouteeTauxMentionsSTI2D;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - STD2A")
    private Double valeurAjouteeTauxMentionsSTD2A;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - STMG")
    private Double valeurAjouteeTauxMentionsSTMG;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - STL")
    private Double valeurAjouteeTauxMentionsSTL;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - ST2S")
    private Double valeurAjouteeTauxMentionsST2S;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - S2TMD")
    private Double valeurAjouteeTauxMentionsS2TMD;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - STHR")
    private Double valeurAjouteeTauxMentionsSTHR;

    @Override
    public Set<ResultatFiliereDto> getResultats() {
        Set<ResultatFiliereDto> resultats = new HashSet<>();

        // L
        if (presentsL != null && presentsL > 0) {
            resultats.add(new ResultatFiliereDto("L", presentsL, tauxReussiteL, valeurAjouteeTauxReussiteL, tauxMentionsL, valeurAjouteeTauxMentionsL));
        }
        // ES
        if (presentsES != null && presentsES > 0) {
            resultats.add(new ResultatFiliereDto("ES", presentsES, tauxReussiteES, valeurAjouteeTauxReussiteES, tauxMentionsES, valeurAjouteeTauxMentionsES));
        }
        // S
        if (presentsS != null && presentsS > 0) {
            resultats.add(new ResultatFiliereDto("S", presentsS, tauxReussiteS, valeurAjouteeTauxReussiteS, tauxMentionsS, valeurAjouteeTauxMentionsS));
        }
        // Gnle
        if (presentsGnle != null && presentsGnle > 0) {
            resultats.add(new ResultatFiliereDto("GENERALE", presentsGnle, tauxReussiteGnle, valeurAjouteeTauxReussiteGnle, tauxMentionsGnle, valeurAjouteeTauxMentionsGnle));
        }
        // STI2D
        if (presentsSTI2D != null && presentsSTI2D > 0) {
            resultats.add(new ResultatFiliereDto("STI2D", presentsSTI2D, tauxReussiteSTI2D, valeurAjouteeTauxReussiteSTI2D, tauxMentionsSTI2D, valeurAjouteeTauxMentionsSTI2D));
        }
        // STD2A
        if (presentsSTD2A != null && presentsSTD2A > 0) {
            resultats.add(new ResultatFiliereDto("STD2A", presentsSTD2A, tauxReussiteSTD2A, valeurAjouteeTauxReussiteSTD2A, tauxMentionsSTD2A, valeurAjouteeTauxMentionsSTD2A));
        }
        // SMG
        if (presentsSTMG != null && presentsSTMG > 0) {
            resultats.add(new ResultatFiliereDto("STMG", presentsSTMG, tauxReussiteSTMG, valeurAjouteeTauxReussiteSTMG, tauxMentionsSTMG, valeurAjouteeTauxMentionsSTMG));
        }
        // STL
        if (presentsSTL != null && presentsSTL > 0) {
            resultats.add(new ResultatFiliereDto("STL", presentsSTL, tauxReussiteSTL, valeurAjouteeTauxReussiteSTL, tauxMentionsSTL, valeurAjouteeTauxMentionsSTL));
        }
        // ST2S
        if (presentsST2S != null && presentsST2S > 0) {
            resultats.add(new ResultatFiliereDto("ST2S", presentsST2S, tauxReussiteST2S, valeurAjouteeTauxReussiteST2S, tauxMentionsST2S, valeurAjouteeTauxMentionsST2S));
        }
        // S2TMD
        if (presentsS2TMD != null && presentsS2TMD > 0) {
            resultats.add(new ResultatFiliereDto("S2TMD", presentsS2TMD, tauxReussiteS2TMD, valeurAjouteeTauxReussiteS2TMD, tauxMentionsS2TMD, valeurAjouteeTauxMentionsS2TMD));
        }
        // STHR
        if (presentsSTHR != null && presentsSTHR > 0) {
            resultats.add(new ResultatFiliereDto("STHR", presentsSTHR, tauxReussiteSTHR, valeurAjouteeTauxReussiteSTHR, tauxMentionsSTHR, valeurAjouteeTauxMentionsSTHR));
        }

        return resultats;
    }


}
