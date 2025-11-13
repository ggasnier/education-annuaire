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
public class IndicateurValeurAjouteeCollegeDataset implements IndicateurValeurAjoutee, Metadata {

    @CsvBindByName(column = "Session")
    private Integer annee;

    @CsvBindByName(column = "UAI")
    private String uai;

    @CsvBindByName(column = "Nb candidats G")
    private Integer effectifCandidatGeneral;
    @CsvBindByName(column = "Taux de réussite G")
    private Double tauxReussiteGeneral;
    @CsvBindByName(column = "VA du taux de réussite G")
    private Double valeurAjouteeTauxReussiteGeneral;
    @CsvBindByName(column = "Note à l'écrit G")
    private Double noteEcritGeneral;
    @CsvBindByName(column = "VA de la note G")
    private Double valeurAjouteeNoteGeneral;

    @CsvBindByName(column = "Nb mentions AB G")
    private Integer nbrMentionABGeneral;
    @CsvBindByName(column = "Nb mentions B G")
    private Integer nbrMentionBGeneral;
    @CsvBindByName(column = "Nb mentions TB G")
    private Integer nbrMentionTBGeneral;
    @CsvBindByName(column = "Nb mentions global G")
    private Integer nbrMentionGlobalGeneral;

    @CsvBindByName(column = "Nb candidats P")
    private Integer effectifCandidatPro;
    @CsvBindByName(column = "Taux de réussite P")
    private Double tauxReussitePro;
    @CsvBindByName(column = "Note à l'écrit P")
    private Double noteEcritPro;

//    @CsvBindByName(column = "Taux d'accès 6eme 3eme")

//    @CsvBindByName(column = "Part présents 3eme ordinaire total")
//    @CsvBindByName(column = "Part présents 3eme ordinaire G")
//    @CsvBindByName(column = "Part présents 3eme ordinaire P")
//    @CsvBindByName(column = "Part présents 3eme segpa total")

    @Override
    public Set<ResultatFiliereDto> getResultats() {
        Set<ResultatFiliereDto> resultats = new HashSet<>();

        // Général
        if (effectifCandidatGeneral != null && effectifCandidatGeneral > 0) {
            resultats.add(new ResultatFiliereDto("BREVETG", effectifCandidatGeneral, tauxReussiteGeneral, valeurAjouteeTauxReussiteGeneral, (double) nbrMentionGlobalGeneral / effectifCandidatGeneral * 100.00, null));
        }
        // Pro
        if (effectifCandidatPro != null && effectifCandidatPro > 0) {
            resultats.add(new ResultatFiliereDto("BREVETPRO", effectifCandidatPro, tauxReussitePro, null, null, null));
        }

        return resultats;
    }
}
