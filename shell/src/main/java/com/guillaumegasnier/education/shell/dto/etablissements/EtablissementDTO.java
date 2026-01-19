package com.guillaumegasnier.education.shell.dto.etablissements;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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
}
