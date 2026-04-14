package com.guillaumegasnier.education.shell.datasets.references;

import com.opencsv.bean.CsvBindByName;
import lombok.Setter;

@Setter
public class DepartementOutreMerDataset extends DepartementDataset {

    @CsvBindByName(column = "COMER")
    protected String code2;

    @Override
    public String getCode() {
        return code2;
    }
}
