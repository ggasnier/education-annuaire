package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "etablissements_jpo")
public class EtablissementJPOEntity extends AbstractEntity {

    @EmbeddedId
    private EtablissementJPOPK pk;

    private LocalTime heureDebut;

    private LocalTime heureFin;

    @Column(columnDefinition = "TEXT")
    private String commentaire;

    @MapsId("uai")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uai", columnDefinition = "BPCHAR(8)", foreignKey = @ForeignKey(name = "fk_jpo_etablissements"), nullable = false)
    private EtablissementEntity etablissement;
}
