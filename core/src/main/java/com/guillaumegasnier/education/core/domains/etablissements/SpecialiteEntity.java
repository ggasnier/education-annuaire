package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "specialites")
public class SpecialiteEntity extends AbstractEntity {

    @EmbeddedId
    private SpecialitePK pk;

    @MapsId("uai")
    @ManyToOne
    private EtablissementEntity etablissement;
}
