package com.guillaumegasnier.education.shell.datasets.etablissements;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Il n'y a pas l'UAI dans ce jeux de données, il faut utiliser le masaId pour le retrouver avec
 */
@Getter
@Setter
@ToString
public class MasaJpoDataset implements Dataset {

    @CsvBindByName(column = "uai_dger_rfa")
    private String masaId;
    //sectuai_cdn
    //sectuai_libusage
    //uai_libusage
    //uailib_com
    //typevt_cdn
    //typevt_libusage
    @CsvBindByName(column = "uaievt_debut_dt")
    private String dateDebut;
    @CsvBindByName(column = "uaievt_fin_dt")
    private String dateFin;
    @CsvBindByName(column = "uaievt_dates_lb")
    private String commentaire;
    //uaievt_comment_txt
    //uai_niv1_libusage
    //comgeo_libusage
    //uai_insee_dep
    //dep_libusage
    //uai_insee_reg
    //reg_libusage
    //anneesco_libusage
    //date_jeu_donnees
}
