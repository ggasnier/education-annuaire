package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "etablissements_options")
public class OptionEtablissementEntity extends AbstractEntity {

    @EmbeddedId
    private OptionEtablissementPK pk;

    @MapsId("uai")
    @ManyToOne
    @JoinColumn(name = "uai", foreignKey = @ForeignKey(name = "fk_options_etablissements"))
    private EtablissementEntity etablissement;
}
