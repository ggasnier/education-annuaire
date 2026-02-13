package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.dto.ResultatFiliereDto;
import com.guillaumegasnier.education.core.validations.etablissements.IndicateurValeurAjoutee;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class IndicateurValeurAjouteeLyceeProDataset implements IndicateurValeurAjoutee, Metadata {

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

    //Presents - Specialites pluri-technologiques de la production
    //Presents - Transformations
    //Presents - Genie civil construction bois
    //Presents - Materiaux souples
    //Presents - Mecanique electricite electronique
    //Presents - Production
    //Presents - Specialites plurivalentes des services
    //Presents - Echanges et gestion
    //Presents - Communication et information
    //Presents - Services aux personnes
    //Presents - Services a la collectivite
    //Presents - Services

    //Taux de reussite - Specialites pluri-technologiques de la production
    //Taux de reussite - Transformations
    //Taux de reussite - Genie civil construction bois
    //Taux de reussite - Materiaux souples
    //Taux de reussite - Mecanique electricite electronique
    //Taux de reussite - Production
    //Taux de reussite - Specialites plurivalentes des services
    //Taux de reussite - Echanges et gestion
    //Taux de reussite - Communication et information
    //Taux de reussite - Services aux personnes
    //Taux de reussite - Services a la collectivite
    //Taux de reussite - Services

    //Valeur ajoutee du taux de reussite - Specialites pluri-technologiques de la production
    //Valeur ajoutee du taux de reussite - Transformations
    //Valeur ajoutee du taux de reussite - Genie civil construction bois
    //Valeur ajoutee du taux de reussite - Materiaux souples
    //Valeur ajoutee du taux de reussite - Mecanique electricite electronique
    //Valeur ajoutee du taux de reussite - Production
    //Valeur ajoutee du taux de reussite - Specialites plurivalentes des services
    //Valeur ajoutee du taux de reussite - Echanges et gestion
    //Valeur ajoutee du taux de reussite - Communication et information
    //Valeur ajoutee du taux de reussite - Services aux personnes
    //Valeur ajoutee du taux de reussite - Services a la collectivite
    //Valeur ajoutee du taux de reussite - Services

    //Effectif de seconde
    //Effectif de premiere
    //Effectif de terminale

    //Taux d'acces 1ere-bac
    //Taux d'acces terminale-bac

    //Valeur ajoutee du taux d'acces 1ere-bac
    //Valeur ajoutee du taux d'acces terminale-bac

    //Taux de mentions - Specialites pluri-technologiques de la production
    //Taux de mentions - Transformations
    //Taux de mentions - Genie civil construction bois
    //Taux de mentions - Materiaux souples
    //Taux de mentions - Mecanique electricite electronique
    //Taux de mentions - Production
    //Taux de mentions - Specialites plurivalentes des services
    //Taux de mentions - Echanges et gestion
    //Taux de mentions - Communication et information
    //Taux de mentions - Services aux personnes
    //Taux de mentions - Services a la collectivite
    //Taux de mentions - Services

    //Valeur ajoutee du taux de mentions - Specialites pluri-technologiques de la production
    //Valeur ajoutee du taux de mentions - Transformations
    //Valeur ajoutee du taux de mentions - Genie civil construction bois
    //Valeur ajoutee du taux de mentions - Materiaux souples
    //Valeur ajoutee du taux de mentions - Mecanique electricite electronique
    //Valeur ajoutee du taux de mentions - Production
    //Valeur ajoutee du taux de mentions - Specialites plurivalentes des services
    //Valeur ajoutee du taux de mentions - Echanges et gestion
    //Valeur ajoutee du taux de mentions - Communication et information
    //Valeur ajoutee du taux de mentions - Services aux personnes
    //Valeur ajoutee du taux de mentions - Services a la collectivite
    //Valeur ajoutee du taux de mentions - Services

    public Set<ResultatFiliereDto> getResultats() {
        Set<ResultatFiliereDto> resultats = new HashSet<>();

        return resultats;
    }

    //Nombre de mentions TB
    //Nombre de mentions B
    //Nombre de mentions AB
}
