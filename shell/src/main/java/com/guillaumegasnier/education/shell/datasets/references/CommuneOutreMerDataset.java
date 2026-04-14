package com.guillaumegasnier.education.shell.datasets.references;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuneOutreMerDataset extends CommuneDataset {

    @CsvBindByName(column = "COM_COMER")
    protected String code2;

    @CsvBindByName(column = "COMER")
    protected String codeDepartement2;

    @Override
    public String getCode() {
        return code2;
    }

    @Override
    public String getCodeDepartement() {
        return codeDepartement2;
    }
}
