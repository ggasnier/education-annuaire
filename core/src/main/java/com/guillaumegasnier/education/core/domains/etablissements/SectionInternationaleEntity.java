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

    @MapsId("uai")
    @ManyToOne
    @JoinColumn(name = "uai", foreignKey = @ForeignKey(name = "fk_ips_etablissements"))
    private EtablissementEntity etablissement;
}
