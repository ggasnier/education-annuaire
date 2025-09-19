package com.guillaumegasnier.education.shell.datasets.ips;

import com.guillaumegasnier.education.shell.datasets.Dataset;

public interface IPSDataset extends Dataset {

    String getUai();

    int getAnnee();

    Double getIndice();

    Double getEcartType();

}
