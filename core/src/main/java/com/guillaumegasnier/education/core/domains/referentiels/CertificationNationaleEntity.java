package com.guillaumegasnier.education.core.domains.referentiels;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.enums.TypologieDiplome;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * RNCP et RS seulement : ne doit pas être confondu avec {@link CertifInfoEntity}
 */
@Getter
@Setter
@Entity
@Table(name = "certifications")
public class CertificationNationaleEntity extends AbstractEntity {

    /**
     * RNCP ou RS + les chiffres
     */
    @Id
    @Column(columnDefinition = "BPCHAR(10)")
    private String code;

    @Column(columnDefinition = "TEXT")
    private String nom;

    private Boolean actif;

    @ManyToOne(fetch = FetchType.LAZY)
    private CertificationNationaleEntity nouvelleCertification;

    /**
     * P : Publiée
     */
    @Column(columnDefinition = "BPCHAR(1)")
    private String etat;

    @Enumerated(EnumType.STRING)
    private TypologieDiplome typologieDiplome;

    private int niveau;

    @Column(columnDefinition = "TEXT")
    private String activitesVisees;

    @Column(columnDefinition = "TEXT")
    private String capacitesAttestees;

    @Column(columnDefinition = "TEXT")
    private String secteursActivite;

    @Column(columnDefinition = "TEXT")
    private String typeEmploiAccessibles;

    @Column(columnDefinition = "TEXT")
    private String prerequisEntreeFormation;

    @Column(columnDefinition = "TEXT")
    private String reglementationsActivites;

    @Column(columnDefinition = "TEXT")
    private String publicationDecret;

    @Column(columnDefinition = "TEXT")
    private String objectifsContexte;

    private Boolean accessibleNouvelleCaledonie;

    private Boolean accessiblePolynesieFrancaise;

    @Column(columnDefinition = "TEXT")
    private String url;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "certifications_metiers",
            joinColumns = @JoinColumn(name = "certification_code"),
            inverseJoinColumns = @JoinColumn(name = "metier_code"),
            foreignKey = @ForeignKey(name = "fk_certifications_metiers_c"),
            inverseForeignKey = @ForeignKey(name = "fk_certifications_metiers_m"))
    private List<MetierEntity> metiers;
}
