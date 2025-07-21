package com.guillaumegasnier.education.shell.datasets.references;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartementOutreMerDataset extends DepartementDataset {

    @CsvBindByName(column = "COMER")
    protected String code;
}
