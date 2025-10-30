package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "etablissements_langues")
public class LangueEntity extends AbstractEntity {

    @EmbeddedId
    private LanguePK pk;

    @ManyToOne
    @JoinColumn(name = "etablissement_id", foreignKey = @ForeignKey(name = "fk_langues_etablissements"), columnDefinition = "UUID", nullable = false)
    private EtablissementEntity etablissement;
}
