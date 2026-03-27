package com.guillaumegasnier.education.core.domains.referentiels;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rome_competences")
public class CompetenceEntity extends AbstractEntity {

    @Id
    private int code;

    private String nom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "macro_competence_code", foreignKey = @ForeignKey(name = "fk_competences_macros"))
    private MacroCompetenceEntity macroCompetence;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "competence")
    private List<MetierCompetenceEntity> metiers;

    public CompetenceEntity(int code, String nom, MacroCompetenceEntity macroCompetence) {
        this.code = code;
        this.nom = nom;
        this.macroCompetence = macroCompetence;
    }
}
