package com.guillaumegasnier.education.annuaire.domains;

import com.guillaumegasnier.education.annuaire.validations.ValidUai;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
}


@Getter
@Setter
@EqualsAndHashCode
@Embeddable
class IndicePositionSocialePK implements Serializable {

    @NotNull
    private int annee;

    @ValidUai
    private String uai;
}
