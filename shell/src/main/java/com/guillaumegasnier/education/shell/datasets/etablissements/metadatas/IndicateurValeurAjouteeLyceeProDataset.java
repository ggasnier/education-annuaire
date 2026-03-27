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
public class IndicateurValeurAjouteeLyceeProDataset implements IndicateurValeurAjouteeLycee {

    @CsvBindByName(column = "Annee")
    private Integer annee;

    @CsvBindByName(column = "UAI")
    private String uai;

    //Presents - Toutes series
    //Taux de reussite - Toutes series
    //Valeur ajoutee du taux de reussite - Toutes series
    @CsvBindByName(column = "Taux d'acces 2nde-bac")
    private Double tauxAcces;
    //Valeur ajoutee du taux d'acces 2nde-bac
    //Taux de mentions - Toutes series
    //Valeur ajoutee du taux de mentions - Toutes series

    // ---- Présents par domaine NSF ----
    @CsvBindByName(column = "Presents - Specialites pluri-technologiques de la production")
    private Integer presentsNsf20;
    @CsvBindByName(column = "Presents - Transformations")
    private Integer presentsNsf22;
    @CsvBindByName(column = "Presents - Genie civil construction bois")
    private Integer presentsNsf23;
    @CsvBindByName(column = "Presents - Materiaux souples")
    private Integer presentsNsf24;
    @CsvBindByName(column = "Presents - Mecanique electricite electronique")
    private Integer presentsNsf25;
    @CsvBindByName(column = "Presents - Production")
    private Integer presentsNsf26;
    @CsvBindByName(column = "Presents - Specialites plurivalentes des services")
    private Integer presentsNsf30;
    @CsvBindByName(column = "Presents - Echanges et gestion")
    private Integer presentsNsf31;
    @CsvBindByName(column = "Presents - Communication et information")
    private Integer presentsNsf32;
    @CsvBindByName(column = "Presents - Services aux personnes")
    private Integer presentsNsf33;
    @CsvBindByName(column = "Presents - Services a la collectivite")
    private Integer presentsNsf34;
    @CsvBindByName(column = "Presents - Services")
    private Integer presentsNsf35;

    // ---- Taux de réussite par domaine NSF ----
    @CsvBindByName(column = "Taux de reussite - Specialites pluri-technologiques de la production")
    private String tauxReussiteNsf20;
    @CsvBindByName(column = "Taux de reussite - Transformations")
    private String tauxReussiteNsf22;
    @CsvBindByName(column = "Taux de reussite - Genie civil construction bois")
    private String tauxReussiteNsf23;
    @CsvBindByName(column = "Taux de reussite - Materiaux souples")
    private String tauxReussiteNsf24;
    @CsvBindByName(column = "Taux de reussite - Mecanique electricite electronique")
    private String tauxReussiteNsf25;
    @CsvBindByName(column = "Taux de reussite - Production")
    private String tauxReussiteNsf26;
    @CsvBindByName(column = "Taux de reussite - Specialites plurivalentes des services")
    private String tauxReussiteNsf30;
    @CsvBindByName(column = "Taux de reussite - Echanges et gestion")
    private String tauxReussiteNsf31;
    @CsvBindByName(column = "Taux de reussite - Communication et information")
    private String tauxReussiteNsf32;
    @CsvBindByName(column = "Taux de reussite - Services aux personnes")
    private String tauxReussiteNsf33;
    @CsvBindByName(column = "Taux de reussite - Services a la collectivite")
    private String tauxReussiteNsf34;
    @CsvBindByName(column = "Taux de reussite - Services")
    private String tauxReussiteNsf35;

    // ---- Valeur ajoutée du taux de réussite par domaine NSF ----
    @CsvBindByName(column = "Valeur ajoutee du taux de reussite - Specialites pluri-technologiques de la production")
    private String valeurAjouteeTauxReussiteNsf20;
    @CsvBindByName(column = "Valeur ajoutee du taux de reussite - Transformations")
    private String valeurAjouteeTauxReussiteNsf22;
    @CsvBindByName(column = "Valeur ajoutee du taux de reussite - Genie civil construction bois")
    private String valeurAjouteeTauxReussiteNsf23;
    @CsvBindByName(column = "Valeur ajoutee du taux de reussite - Materiaux souples")
    private String valeurAjouteeTauxReussiteNsf24;
    @CsvBindByName(column = "Valeur ajoutee du taux de reussite - Mecanique electricite electronique")
    private String valeurAjouteeTauxReussiteNsf25;
    @CsvBindByName(column = "Valeur ajoutee du taux de reussite - Production")
    private String valeurAjouteeTauxReussiteNsf26;
    @CsvBindByName(column = "Valeur ajoutee du taux de reussite - Specialites plurivalentes des services")
    private String valeurAjouteeTauxReussiteNsf30;
    @CsvBindByName(column = "Valeur ajoutee du taux de reussite - Echanges et gestion")
    private String valeurAjouteeTauxReussiteNsf31;
    @CsvBindByName(column = "Valeur ajoutee du taux de reussite - Communication et information")
    private String valeurAjouteeTauxReussiteNsf32;
    @CsvBindByName(column = "Valeur ajoutee du taux de reussite - Services aux personnes")
    private String valeurAjouteeTauxReussiteNsf33;
    @CsvBindByName(column = "Valeur ajoutee du taux de reussite - Services a la collectivite")
    private String valeurAjouteeTauxReussiteNsf34;
    @CsvBindByName(column = "Valeur ajoutee du taux de reussite - Services")
    private String valeurAjouteeTauxReussiteNsf35;

    // ---- Effectifs ----
    @CsvBindByName(column = "Effectif de seconde")
    private Integer effectifSeconde;
    @CsvBindByName(column = "Effectif de premiere")
    private Integer effectifPremiere;
    @CsvBindByName(column = "Effectif de terminale")
    private Integer effectifTerminale;

    // ---- Taux d'accès ----
    @CsvBindByName(column = "Taux d'acces 1ere-bac")
    private Double tauxAcces1ereBac;
    @CsvBindByName(column = "Taux d'acces terminale-bac")
    private Double tauxAccesTerminaleBac;
    @CsvBindByName(column = "Valeur ajoutee du taux d'acces 1ere-bac")
    private String valeurAjouteeTauxAcces1ereBac;
    @CsvBindByName(column = "Valeur ajoutee du taux d'acces terminale-bac")
    private String valeurAjouteeTauxAccesTerminaleBac;

    // ---- Taux de mentions par domaine NSF ----
    @CsvBindByName(column = "Taux de mentions - Specialites pluri-technologiques de la production")
    private String tauxMentionsNsf20;
    @CsvBindByName(column = "Taux de mentions - Transformations")
    private String tauxMentionsNsf22;
    @CsvBindByName(column = "Taux de mentions - Genie civil construction bois")
    private String tauxMentionsNsf23;
    @CsvBindByName(column = "Taux de mentions - Materiaux souples")
    private String tauxMentionsNsf24;
    @CsvBindByName(column = "Taux de mentions - Mecanique electricite electronique")
    private String tauxMentionsNsf25;
    @CsvBindByName(column = "Taux de mentions - Production")
    private String tauxMentionsNsf26;
    @CsvBindByName(column = "Taux de mentions - Specialites plurivalentes des services")
    private String tauxMentionsNsf30;
    @CsvBindByName(column = "Taux de mentions - Echanges et gestion")
    private String tauxMentionsNsf31;
    @CsvBindByName(column = "Taux de mentions - Communication et information")
    private String tauxMentionsNsf32;
    @CsvBindByName(column = "Taux de mentions - Services aux personnes")
    private String tauxMentionsNsf33;
    @CsvBindByName(column = "Taux de mentions - Services a la collectivite")
    private String tauxMentionsNsf34;
    @CsvBindByName(column = "Taux de mentions - Services")
    private String tauxMentionsNsf35;

    // ---- Valeur ajoutée du taux de mentions par domaine NSF ----
    @CsvBindByName(column = "Valeur ajoutee du taux de mentions - Specialites pluri-technologiques de la production")
    private String valeurAjouteeTauxMentionsNsf20;
    @CsvBindByName(column = "Valeur ajoutee du taux de mentions - Transformations")
    private String valeurAjouteeTauxMentionsNsf22;
    @CsvBindByName(column = "Valeur ajoutee du taux de mentions - Genie civil construction bois")
    private String valeurAjouteeTauxMentionsNsf23;
    @CsvBindByName(column = "Valeur ajoutee du taux de mentions - Materiaux souples")
    private String valeurAjouteeTauxMentionsNsf24;
    @CsvBindByName(column = "Valeur ajoutee du taux de mentions - Mecanique electricite electronique")
    private String valeurAjouteeTauxMentionsNsf25;
    @CsvBindByName(column = "Valeur ajoutee du taux de mentions - Production")
    private String valeurAjouteeTauxMentionsNsf26;
    @CsvBindByName(column = "Valeur ajoutee du taux de mentions - Specialites plurivalentes des services")
    private String valeurAjouteeTauxMentionsNsf30;
    @CsvBindByName(column = "Valeur ajoutee du taux de mentions - Echanges et gestion")
    private String valeurAjouteeTauxMentionsNsf31;
    @CsvBindByName(column = "Valeur ajoutee du taux de mentions - Communication et information")
    private String valeurAjouteeTauxMentionsNsf32;
    @CsvBindByName(column = "Valeur ajoutee du taux de mentions - Services aux personnes")
    private String valeurAjouteeTauxMentionsNsf33;
    @CsvBindByName(column = "Valeur ajoutee du taux de mentions - Services a la collectivite")
    private String valeurAjouteeTauxMentionsNsf34;
    @CsvBindByName(column = "Valeur ajoutee du taux de mentions - Services")
    private String valeurAjouteeTauxMentionsNsf35;

    // ---- Mentions globales ----
    @CsvBindByName(column = "Nombre de mentions TB")
    private Integer nbrMentionsTB;
    @CsvBindByName(column = "Nombre de mentions B")
    private Integer nbrMentionsB;
    @CsvBindByName(column = "Nombre de mentions AB")
    private Integer nbrMentionsAB;

    @Override
    public Set<ResultatFiliereDto> getResultats() {
        Set<ResultatFiliereDto> resultats = new HashSet<>();

        addIfPresent(resultats, Filiere.PRO_NSF20, presentsNsf20, tauxReussiteNsf20, valeurAjouteeTauxReussiteNsf20, tauxMentionsNsf20, valeurAjouteeTauxMentionsNsf20);
        addIfPresent(resultats, Filiere.PRO_NSF22, presentsNsf22, tauxReussiteNsf22, valeurAjouteeTauxReussiteNsf22, tauxMentionsNsf22, valeurAjouteeTauxMentionsNsf22);
        addIfPresent(resultats, Filiere.PRO_NSF23, presentsNsf23, tauxReussiteNsf23, valeurAjouteeTauxReussiteNsf23, tauxMentionsNsf23, valeurAjouteeTauxMentionsNsf23);
        addIfPresent(resultats, Filiere.PRO_NSF24, presentsNsf24, tauxReussiteNsf24, valeurAjouteeTauxReussiteNsf24, tauxMentionsNsf24, valeurAjouteeTauxMentionsNsf24);
        addIfPresent(resultats, Filiere.PRO_NSF25, presentsNsf25, tauxReussiteNsf25, valeurAjouteeTauxReussiteNsf25, tauxMentionsNsf25, valeurAjouteeTauxMentionsNsf25);
        addIfPresent(resultats, Filiere.PRO_NSF26, presentsNsf26, tauxReussiteNsf26, valeurAjouteeTauxReussiteNsf26, tauxMentionsNsf26, valeurAjouteeTauxMentionsNsf26);
        addIfPresent(resultats, Filiere.PRO_NSF30, presentsNsf30, tauxReussiteNsf30, valeurAjouteeTauxReussiteNsf30, tauxMentionsNsf30, valeurAjouteeTauxMentionsNsf30);
        addIfPresent(resultats, Filiere.PRO_NSF31, presentsNsf31, tauxReussiteNsf31, valeurAjouteeTauxReussiteNsf31, tauxMentionsNsf31, valeurAjouteeTauxMentionsNsf31);
        addIfPresent(resultats, Filiere.PRO_NSF32, presentsNsf32, tauxReussiteNsf32, valeurAjouteeTauxReussiteNsf32, tauxMentionsNsf32, valeurAjouteeTauxMentionsNsf32);
        addIfPresent(resultats, Filiere.PRO_NSF33, presentsNsf33, tauxReussiteNsf33, valeurAjouteeTauxReussiteNsf33, tauxMentionsNsf33, valeurAjouteeTauxMentionsNsf33);
        addIfPresent(resultats, Filiere.PRO_NSF34, presentsNsf34, tauxReussiteNsf34, valeurAjouteeTauxReussiteNsf34, tauxMentionsNsf34, valeurAjouteeTauxMentionsNsf34);
        addIfPresent(resultats, Filiere.PRO_NSF35, presentsNsf35, tauxReussiteNsf35, valeurAjouteeTauxReussiteNsf35, tauxMentionsNsf35, valeurAjouteeTauxMentionsNsf35);

        return resultats;
    }

    private void addIfPresent(Set<ResultatFiliereDto> resultats, Filiere filiere,
                              Integer presents, String tauxReussite, String valeurAjouteeReussite,
                              String tauxMentions, String valeurAjouteeMentions) {
        if (presents != null && presents > 0) {
            resultats.add(new ResultatFiliereDto(
                    filiere,
                    presents,
                    tauxAcces,
                    formatDouble(tauxReussite),
                    formatDouble(valeurAjouteeReussite),
                    formatDouble(tauxMentions),
                    formatDouble(valeurAjouteeMentions)
            ));
        }
    }
}
