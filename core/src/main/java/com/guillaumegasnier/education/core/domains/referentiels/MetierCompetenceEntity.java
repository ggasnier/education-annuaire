package com.guillaumegasnier.education.core.domains.referentiels;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.enums.CoeurMetier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rome_metiers_competences")
public class MetierCompetenceEntity extends AbstractEntity {

    @EmbeddedId
    private MetierCompetencePK pk;

    @Column(columnDefinition = "BPCHAR(1)")
    @Enumerated(EnumType.STRING)
    private CoeurMetier coeurMetier;

    @MapsId("metierCode")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metier_code", foreignKey = @ForeignKey(name = "fk_metiers_competences_m"), columnDefinition = "BPCHAR(5)")
    private MetierEntity metier;

    @MapsId("competenceCode")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competence_code", foreignKey = @ForeignKey(name = "fk_metiers_competences_c"))
    private CompetenceEntity competence;
}
