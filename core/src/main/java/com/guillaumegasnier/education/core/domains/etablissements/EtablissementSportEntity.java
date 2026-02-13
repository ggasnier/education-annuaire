package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractSourcesEntity;
import com.guillaumegasnier.education.core.enums.Sport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "etablissements_sports")
public class EtablissementSportEntity extends AbstractSourcesEntity {

    @EmbeddedId
    private EtablissementSportPK pk;

    @MapsId("uai")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uai", columnDefinition = "BPCHAR(8)", foreignKey = @ForeignKey(name = "fk_sports_etablissements"), nullable = false)
    private EtablissementEntity etablissement;

    public EtablissementSportEntity(String uai, Sport sport, Sport.Categorie categorie, EtablissementEntity etablissement) {
        this.pk = new EtablissementSportPK(uai, sport, categorie);
        this.etablissement = etablissement;
    }
}
