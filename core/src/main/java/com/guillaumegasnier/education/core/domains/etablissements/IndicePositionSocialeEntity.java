package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "etablissements_ips")
public class IndicePositionSocialeEntity extends AbstractEntity {

    @EmbeddedId
    private IndicePositionSocialePK pk;

    @MapsId("uai")
    @ManyToOne
    @JoinColumn(name = "uai", foreignKey = @ForeignKey(name = "fk_ips_etablissements"))
    private EtablissementEntity etablissement;

    @Column(columnDefinition = "VARCHAR(1)")
    private String categorie;

    /**
     * IPS de l'établissement
     */
    private Double indice;

    /**
     * Ecart-type établissement
     */
    private Double ecartType;

}


