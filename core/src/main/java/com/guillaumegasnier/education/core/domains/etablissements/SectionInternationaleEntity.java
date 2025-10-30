package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "etablissements_sections_internationales")
public class SectionInternationaleEntity extends AbstractEntity {

    @EmbeddedId
    private SectionInternationalePK pk;

    @ManyToOne
    @JoinColumn(name = "etablissement_id", foreignKey = @ForeignKey(name = "fk_sections_internationales_etablissements"), columnDefinition = "UUID", nullable = false)
    private EtablissementEntity etablissement;
}
