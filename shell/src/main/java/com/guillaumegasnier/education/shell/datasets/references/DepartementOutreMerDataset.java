package com.guillaumegasnier.education.shell.datasets.references;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartementOutreMerDataset extends DepartementDataset {

    @CsvBindByName(column = "COMER")
    protected String code;
}
