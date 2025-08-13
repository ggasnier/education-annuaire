package com.guillaumegasnier.education.core.domains.etablissements;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sections")
public class SectionSportiveEntity {

    @EmbeddedId
    private SectionSportivePK pk;

    @MapsId("uai")
    @ManyToOne
    @JoinColumn(name = "uai", foreignKey = @ForeignKey(name = "fk_sections_etablissements"))
    private EtablissementEntity etablissement;

}
