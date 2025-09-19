package com.guillaumegasnier.education.shell.datasets.ips;

import com.guillaumegasnier.education.shell.datasets.Dataset;


public interface IPSDataset extends Dataset {

//    /**
//     * <ul>
//     *     <li>E : école</li>
//     *     <li>C : collège</li>
//     *     <li>L : lycée</li>
//     *     <li>R : établissements régionaux d'enseignement adapté (EREA)</li>
//     * </ul>
//     */
//    String getCategorie();

    String getUai();

    int getAnnee();

    Double getIndice();

    Double getEcartType();

}
