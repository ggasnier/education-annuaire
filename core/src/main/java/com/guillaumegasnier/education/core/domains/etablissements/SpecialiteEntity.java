package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "etablissements_specialites")
public class SpecialiteEntity extends AbstractEntity {

    @EmbeddedId
    private SpecialitePK pk;

    @ManyToOne
    @JoinColumn(name = "etablissement_id", foreignKey = @ForeignKey(name = "fk_specialites_etablissements"), columnDefinition = "UUID", nullable = false)
    private EtablissementEntity etablissement;
}
