package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Deprecated
@Getter
@Setter
@Entity
@Table(name = "etablissements_sections_sportives")
public class SectionSportiveEntity extends AbstractEntity {

    @EmbeddedId
    private SectionSportivePK pk;

    @MapsId("uai")
    @ManyToOne
    @JoinColumn(name = "uai", columnDefinition = "BPCHAR(8)", foreignKey = @ForeignKey(name = "fk_sections_sportives_etablissements"), nullable = false)
    private EtablissementEntity etablissement;
}
