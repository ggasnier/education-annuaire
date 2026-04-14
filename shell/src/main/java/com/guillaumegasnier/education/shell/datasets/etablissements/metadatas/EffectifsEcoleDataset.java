package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.etablissements.Effectifs;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EffectifsEcoleDataset implements Effectifs, Metadata {

    @CsvBindByName(column = "Numéro de l'école")
    private String uai;

    @CsvBindByName(column = "Rentrée scolaire")
    private Integer annee;

    @CsvBindByName(column = "Nombre total d'élèves")
    private Integer effectifs;
}
