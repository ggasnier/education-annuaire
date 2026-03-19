package com.guillaumegasnier.education.core.domains.referentiels;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rome_macros_competences")
public class MacroCompetenceEntity extends AbstractEntity {

    @Id
    @Column(columnDefinition = "BPCHAR(5)", length = 5)
    private String code;

    private String nom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "objectif_code", foreignKey = @ForeignKey(name = "fk_macros_competences_enjeux"))
    private ObjectifEntity objectif;
}
