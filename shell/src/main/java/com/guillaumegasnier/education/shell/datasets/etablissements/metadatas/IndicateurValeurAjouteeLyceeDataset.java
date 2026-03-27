package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.dto.ResultatFiliereDto;
import com.guillaumegasnier.education.core.enums.Filiere;
import com.guillaumegasnier.education.core.validations.etablissements.IndicateurValeurAjouteeLycee;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.formatDouble;

@Getter
@Setter
public class IndicateurValeurAjouteeLyceeDataset implements IndicateurValeurAjouteeLycee {

    @CsvBindByName(column = "Année")
    private Integer annee;

    @CsvBindByName(column = "UAI")
    private String uai;

    //Présents - Toutes séries

    //Taux de réussite - Toutes séries

    //Valeur ajoutée du taux de réussite - Toutes séries

    @CsvBindByName(column = "Taux d'accès 2nde-bac")
    private String tauxAcces;
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

    //Valeur ajoutée du taux d'acces 2nde-bac

    //Taux de mentions - Toutes séries
    @CsvBindByName(column = "Effectif de premiere")
    private Integer effectifPremiere;
    @CsvBindByName(column = "Effectif de terminale")
    private Integer effectifTerminale;
    @CsvBindByName(column = "Taux d'accès 1ere-bac")
    private String tauxAcces1ereBac;
    @CsvBindByName(column = "Taux d'accès terminale-bac")
    private String tauxAccesTerminaleBac;
    @CsvBindByName(column = "Valeur ajoutée du taux d'accès 1ere-bac")
    private String valeurAjouteeTauxAcces1ereBac;
    @CsvBindByName(column = "Valeur ajoutée du taux d'accès terminale-bac")
    private String valeurAjouteeTauxAccesTerminaleBac;
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
    private String tauxReussiteL;
    @CsvBindByName(column = "Taux de réussite - ES")
    private String tauxReussiteES;
    @CsvBindByName(column = "Taux de réussite - S")
    private String tauxReussiteS;
    @CsvBindByName(column = "Taux de réussite - Gnle")
    private String tauxReussiteGnle;
    @CsvBindByName(column = "Taux de réussite - STI2D")
    private String tauxReussiteSTI2D;
    @CsvBindByName(column = "Taux de réussite - STD2A")
    private String tauxReussiteSTD2A;
    @CsvBindByName(column = "Taux de réussite - STMG")
    private String tauxReussiteSTMG;
    @CsvBindByName(column = "Taux de réussite - STL")
    private String tauxReussiteSTL;
    @CsvBindByName(column = "Taux de réussite - ST2S")
    private String tauxReussiteST2S;
    @CsvBindByName(column = "Taux de réussite - S2TMD")
    private String tauxReussiteS2TMD;
    @CsvBindByName(column = "Taux de réussite - STHR")
    private String tauxReussiteSTHR;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - L")
    private String valeurAjouteeTauxReussiteL;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - ES")
    private String valeurAjouteeTauxReussiteES;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - S")
    private String valeurAjouteeTauxReussiteS;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - Gnle")
    private String valeurAjouteeTauxReussiteGnle;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - STI2D")
    private String valeurAjouteeTauxReussiteSTI2D;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - STD2A")
    private String valeurAjouteeTauxReussiteSTD2A;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - STMG")
    private String valeurAjouteeTauxReussiteSTMG;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - STL")
    private String valeurAjouteeTauxReussiteSTL;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - ST2S")
    private String valeurAjouteeTauxReussiteST2S;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - S2TMD")
    private String valeurAjouteeTauxReussiteS2TMD;
    @CsvBindByName(column = "Valeur ajoutée du taux de réussite - STHR")
    private String valeurAjouteeTauxReussiteSTHR;
    @CsvBindByName(column = "Taux de mentions - L")
    private String tauxMentionsL;
    @CsvBindByName(column = "Taux de mentions - ES")
    private String tauxMentionsES;
    @CsvBindByName(column = "Taux de mentions - S")
    private String tauxMentionsS;
    @CsvBindByName(column = "Taux de mentions - Gnle")
    private String tauxMentionsGnle;
    @CsvBindByName(column = "Taux de mentions - STI2D")
    private String tauxMentionsSTI2D;
    @CsvBindByName(column = "Taux de mentions - STD2A")
    private String tauxMentionsSTD2A;
    @CsvBindByName(column = "Taux de mentions - STMG")
    private String tauxMentionsSTMG;
    @CsvBindByName(column = "Taux de mentions - STL")
    private String tauxMentionsSTL;
    @CsvBindByName(column = "Taux de mentions - ST2S")
    private String tauxMentionsST2S;
    @CsvBindByName(column = "Taux de mentions - S2TMD")
    private String tauxMentionsS2TMD;
    @CsvBindByName(column = "Taux de mentions - STHR")
    private String tauxMentionsSTHR;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - L")
    private String valeurAjouteeTauxMentionsL;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - ES")
    private String valeurAjouteeTauxMentionsES;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - S")
    private String valeurAjouteeTauxMentionsS;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - Gnle")
    private String valeurAjouteeTauxMentionsGnle;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - STI2D")
    private String valeurAjouteeTauxMentionsSTI2D;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - STD2A")
    private String valeurAjouteeTauxMentionsSTD2A;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - STMG")
    private String valeurAjouteeTauxMentionsSTMG;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - STL")
    private String valeurAjouteeTauxMentionsSTL;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - ST2S")
    private String valeurAjouteeTauxMentionsST2S;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - S2TMD")
    private String valeurAjouteeTauxMentionsS2TMD;
    @CsvBindByName(column = "Valeur ajoutée du taux de mentions - STHR")
    private String valeurAjouteeTauxMentionsSTHR;

    @Override
    public Double getTauxAcces() {
        return formatDouble(tauxAcces);
    }

    @Override
    public Set<ResultatFiliereDto> getResultats() {
        Set<ResultatFiliereDto> resultats = new HashSet<>();

        // L
        if (presentsL != null && presentsL > 0) {
            resultats.add(new ResultatFiliereDto(Filiere.L, presentsL, formatDouble(tauxAcces), formatDouble(tauxReussiteL), formatDouble(valeurAjouteeTauxReussiteL), formatDouble(tauxMentionsL), formatDouble(valeurAjouteeTauxMentionsL)));
        }
        // ES
        if (presentsES != null && presentsES > 0) {
            resultats.add(new ResultatFiliereDto(Filiere.ES, presentsES, formatDouble(tauxAcces), formatDouble(tauxReussiteES), formatDouble(valeurAjouteeTauxReussiteES), formatDouble(tauxMentionsES), formatDouble(valeurAjouteeTauxMentionsES)));
        }
        // S
        if (presentsS != null && presentsS > 0) {
            resultats.add(new ResultatFiliereDto(Filiere.S, presentsS, formatDouble(tauxAcces), formatDouble(tauxReussiteS), formatDouble(valeurAjouteeTauxReussiteS), formatDouble(tauxMentionsS), formatDouble(valeurAjouteeTauxMentionsS)));
        }
        // Gnle
        if (presentsGnle != null && presentsGnle > 0) {
            resultats.add(new ResultatFiliereDto(Filiere.GENERALE, presentsGnle, formatDouble(tauxAcces), formatDouble(tauxReussiteGnle), formatDouble(valeurAjouteeTauxReussiteGnle), formatDouble(tauxMentionsGnle), formatDouble(valeurAjouteeTauxMentionsGnle)));
        }
        // STI2D
        if (presentsSTI2D != null && presentsSTI2D > 0) {
            resultats.add(new ResultatFiliereDto(Filiere.STI2D, presentsSTI2D, formatDouble(tauxAcces), formatDouble(tauxReussiteSTI2D), formatDouble(valeurAjouteeTauxReussiteSTI2D), formatDouble(tauxMentionsSTI2D), formatDouble(valeurAjouteeTauxMentionsSTI2D)));
        }
        // STD2A
        if (presentsSTD2A != null && presentsSTD2A > 0) {
            resultats.add(new ResultatFiliereDto(Filiere.STD2A, presentsSTD2A, formatDouble(tauxAcces), formatDouble(tauxReussiteSTD2A), formatDouble(valeurAjouteeTauxReussiteSTD2A), formatDouble(tauxMentionsSTD2A), formatDouble(valeurAjouteeTauxMentionsSTD2A)));
        }
        // SMG
        if (presentsSTMG != null && presentsSTMG > 0) {
            resultats.add(new ResultatFiliereDto(Filiere.STMG, presentsSTMG, formatDouble(tauxAcces), formatDouble(tauxReussiteSTMG), formatDouble(valeurAjouteeTauxReussiteSTMG), formatDouble(tauxMentionsSTMG), formatDouble(valeurAjouteeTauxMentionsSTMG)));
        }
        // STL
        if (presentsSTL != null && presentsSTL > 0) {
            resultats.add(new ResultatFiliereDto(Filiere.STL, presentsSTL, formatDouble(tauxAcces), formatDouble(tauxReussiteSTL), formatDouble(valeurAjouteeTauxReussiteSTL), formatDouble(tauxMentionsSTL), formatDouble(valeurAjouteeTauxMentionsSTL)));
        }
        // ST2S
        if (presentsST2S != null && presentsST2S > 0) {
            resultats.add(new ResultatFiliereDto(Filiere.ST2S, presentsST2S, formatDouble(tauxAcces), formatDouble(tauxReussiteST2S), formatDouble(valeurAjouteeTauxReussiteST2S), formatDouble(tauxMentionsST2S), formatDouble(valeurAjouteeTauxMentionsST2S)));
        }
        // S2TMD
        if (presentsS2TMD != null && presentsS2TMD > 0) {
            resultats.add(new ResultatFiliereDto(Filiere.S2TMD, presentsS2TMD, formatDouble(tauxAcces), formatDouble(tauxReussiteS2TMD), formatDouble(valeurAjouteeTauxReussiteS2TMD), formatDouble(tauxMentionsS2TMD), formatDouble(valeurAjouteeTauxMentionsS2TMD)));
        }
        // STHR
        if (presentsSTHR != null && presentsSTHR > 0) {
            resultats.add(new ResultatFiliereDto(Filiere.STHR, presentsSTHR, formatDouble(tauxAcces), formatDouble(tauxReussiteSTHR), formatDouble(valeurAjouteeTauxReussiteSTHR), formatDouble(tauxMentionsSTHR), formatDouble(valeurAjouteeTauxMentionsSTHR)));
        }

        return resultats;
    }

}
