package com.guillaumegasnier.education.annuaire.domains;

import com.guillaumegasnier.education.annuaire.validations.ValidUai;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ContactPk implements Serializable {

    @ValidUai
    @Column(columnDefinition = "CHAR(8)", updatable = false, nullable = false, length = 8)
    private String uai;

    @NotNull
    @NotBlank
    private String clef;

    @NotNull
    @NotBlank
    private String valeur;
    
//    public ContactPk(String uai, String clef, String valeur) {
//        this.uai = uai;
//        this.clef = clef;
//        this.valeur = valeur;
//    }
}
