package com.guillaumegasnier.education.shell.datasets.ips;

import com.opencsv.bean.CsvBindByName;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;

@Deprecated
@Setter
public class IPSGlobalDataset implements IPSDataset {

    @CsvBindByName(column = "UAI")
    private String uai;

    @CsvBindByName(column = "Rentrée scolaire")
    private String rentreeScolaire;

    @CsvBindByName(column = "IPS")
    private String indice1;

    @CsvBindByName(column = "IPS de l'établissement")
    private String indice2;

    @CsvBindByName(column = "IPS Ensemble GT-PRO")
    private String indice3;

    @CsvBindByName(column = "Ecart-type de l'IPS")
    private String ecartType1;

    @CsvBindByName(column = "Ecart type de l'IPS")
    private String ecartType2;

    @CsvBindByName(column = "Ecart-type établissement")
    private String ecartType3;

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Nullable
    private Double getDouble(@NonNull List<String> valeurs) {
        return valeurs.stream()
                .filter(s -> s != null && !s.isBlank())
                .filter(IPSGlobalDataset::isDouble)
                .map(Double::valueOf)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getUai() {
        return uai;
    }

    @Override
    public int getAnnee() {
        return Integer.parseInt(rentreeScolaire.substring(0, 4));
    }

    @Override
    public Double getIndice() {
        return getDouble(Arrays.asList(indice1, indice2, indice3));
    }

    @Override
    public Double getEcartType() {
        return getDouble(Arrays.asList(ecartType1, ecartType2, ecartType3));
    }
}
