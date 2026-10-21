package com.guillaumegasnier.education.core.domains.referentiels;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.enums.OuiNon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rome_savoirs")
public class MetierSavoirEntity extends AbstractEntity {

    @Id
    private int code;

    private String nom;

    private String categorie;

    private String sousCategorie;

    @Column(columnDefinition = "BPCHAR(1)", length = 1)
    @Enumerated(EnumType.STRING)
    private OuiNon transitionEco;

    /**
     * 1 :
     * 2 :
     * 3 :
     */
    private int codeRubrique;

}
