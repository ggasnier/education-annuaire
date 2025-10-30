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

    @ManyToOne
    @JoinColumn(name = "etablissement_id", foreignKey = @ForeignKey(name = "fk_ips_etablissements"), columnDefinition = "UUID", nullable = false)
    private EtablissementEntity etablissement;

    @Column(columnDefinition = "BPCHAR(1)")
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


