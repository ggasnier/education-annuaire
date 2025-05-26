package com.guillaumegasnier.education.annuaire.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ips")
public class IndicePositionSocialeEntity {

    @EmbeddedId
    private IndicePositionSocialePK pk;

    @MapsId("uai")
    @ManyToOne
    private EtablissementEntity etablissement;

    /**
     * IPS de l'établissement
     */
    private Double indice;

    /**
     * Ecart-type établissement
     */
    private Double ecartType;

    public IndicePositionSocialeEntity() {
        this.pk = new IndicePositionSocialePK();
    }

    public IndicePositionSocialeEntity(String uai, int annee) {
        this.pk = new IndicePositionSocialePK(uai, annee);
    }
}


