package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Le MASA (Ministère de l'Agriculture, de l'Agro-alimentaire et de la Souveraineté Alimentaire) utilise une codition interne pour identifier les établissements
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "etablissements_masa")
public class EtablissementMasaEntity extends AbstractEntity {

    /**
     * Appelé Code DGER, uai_dger_rfa
     */
    @Id
    @Column(columnDefinition = "BPCHAR(8)")
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uai", columnDefinition = "BPCHAR(8)", foreignKey = @ForeignKey(name = "fk_masa_etablissements"), nullable = false)
    private EtablissementEntity etablissement;
}
