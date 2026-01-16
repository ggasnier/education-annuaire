package com.guillaumegasnier.education.shell.dto.etablissements;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.AbstractMap;

@Getter
@Setter
@ToString
public class EtablissementDTO {

    private String uai;
    private String siret;
    private String nom;
    private String codeNature;
    private String codeContrat;
    private LocalDate dateOuverture;
    private LocalDate dateFermeture;
    private Boolean actif;
    private String codeCommune;
    private String nomCommune;
    private AbstractMap.SimpleEntry<String, String> externalId;

    public Boolean isActif() {
        return actif;
    }
}
