package com.guillaumegasnier.education.shell.datasets.references;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuneOutreMerDataset extends CommuneDataset {

    @CsvBindByName(column = "COM_COMER")
    protected String code;

    @CsvBindByName(column = "COMER")
    protected String codeDepartement;
}
