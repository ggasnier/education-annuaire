package com.guillaumegasnier.education.core.domains.referentiels;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rome_metiers_savoirs")
public class MetierSavoirLienEntity {

    @EmbeddedId
    private MetierSavoirLienPK pk;

    @MapsId("codeMetier")
    @ManyToOne(fetch = FetchType.LAZY)
    private MetierEntity metier;

    @MapsId("codeSavoir")
    @ManyToOne(fetch = FetchType.LAZY)
    private MetierSavoirEntity savoir;

    @Column(columnDefinition = "BPCHAR(1)", length = 1)
    private String coeurMetier;
}
