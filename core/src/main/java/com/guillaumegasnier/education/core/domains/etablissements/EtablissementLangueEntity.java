package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * LV1, LV2, LCA
 * Section européenne
 */
@Getter
@Setter
@Entity
@Table(name = "etablissements_langues")
public class EtablissementLangueEntity extends AbstractEntity {

    @EmbeddedId
    private EtablissementLanguePK pk;

    @MapsId("uai")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uai", columnDefinition = "BPCHAR(8)", foreignKey = @ForeignKey(name = "fk_langues_etablissements"), nullable = false)
    private EtablissementEntity etablissement;
}
