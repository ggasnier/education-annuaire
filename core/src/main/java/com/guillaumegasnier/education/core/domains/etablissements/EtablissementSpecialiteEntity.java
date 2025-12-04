package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "etablissements_specialites")
public class EtablissementSpecialiteEntity extends AbstractEntity {

    @EmbeddedId
    private EtablissementSpecialitePK pk;

    @MapsId("uai")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uai", columnDefinition = "BPCHAR(8)", foreignKey = @ForeignKey(name = "fk_specialites_etablissements"), nullable = false)
    private EtablissementEntity etablissement;
}
