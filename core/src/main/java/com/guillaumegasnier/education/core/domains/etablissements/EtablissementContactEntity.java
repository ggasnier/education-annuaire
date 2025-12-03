package com.guillaumegasnier.education.core.domains.etablissements;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "etablissements_contacts")
public class EtablissementContactEntity {

    @EmbeddedId
    private EtablissementContactPK pk;

    @MapsId("uai")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uai", columnDefinition = "BPCHAR(8)", foreignKey = @ForeignKey(name = "fk_langues_etablissements"), nullable = false)
    private EtablissementEntity etablissement;
}
