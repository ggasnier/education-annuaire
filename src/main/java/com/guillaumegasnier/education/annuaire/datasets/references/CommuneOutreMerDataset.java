package com.guillaumegasnier.education.annuaire.datasets.references;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class CommuneOutreMerDataset extends CommuneDataset {

    @CsvBindByName(column = "COM_COMER")
    protected String code;

    @CsvBindByName(column = "COMER")
    protected String codeDepartement;
}
