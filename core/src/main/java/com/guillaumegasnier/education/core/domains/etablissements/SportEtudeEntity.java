package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "etablissements_sports_etudes")
public class SportEtudeEntity extends AbstractEntity {

    @EmbeddedId
    private SportEtudePK pk;

    @ManyToOne
    @JoinColumn(name = "etablissement_id", foreignKey = @ForeignKey(name = "fk_sports_etudes_etablissements"), columnDefinition = "UUID", nullable = false)
    private EtablissementEntity etablissement;
}
