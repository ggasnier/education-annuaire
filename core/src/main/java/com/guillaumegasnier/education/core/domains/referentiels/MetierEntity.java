package com.guillaumegasnier.education.core.domains.referentiels;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.dto.MetierMetadataDto;
import com.guillaumegasnier.education.core.enums.GrandDomaine;
import com.guillaumegasnier.education.core.enums.OuiNon;
import com.guillaumegasnier.education.core.enums.TransitionEcologique;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

/**
 * Répertoire Opérationnel des Métiers et des Emplois
 */
@Getter
@Setter
@Entity
@Table(name = "rome_metiers")
public class MetierEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "BPCHAR(5)", length = 5, nullable = false)
    private String code;

    private String nom;

    @Type(JsonType.class)
    @Column(name = "metadatas", columnDefinition = "jsonb")
    private MetierMetadataDto metdatas = new MetierMetadataDto();

    @ManyToOne(fetch = FetchType.LAZY)
    private MetierEntity metierParent;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "metiers")
    private List<CertificationNationaleEntity> certifications;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "metier")
    private List<MetierCompetenceEntity> competences;

    @Column(columnDefinition = "BPCHAR(1)")
    @Enumerated(EnumType.STRING)
    private TransitionEcologique transitionEco;

    @Column(columnDefinition = "BPCHAR(1)")
    @Enumerated(EnumType.STRING)
    private OuiNon transitionNum;

    @Column(columnDefinition = "BPCHAR(1)")
    @Enumerated(EnumType.STRING)
    private OuiNon transitionDemo;

    @Column(columnDefinition = "BPCHAR(1)")
    @Enumerated(EnumType.STRING)
    private OuiNon emploiReglemente;

    @Column(columnDefinition = "BPCHAR(1)")
    @Enumerated(EnumType.STRING)
    private OuiNon emploiCadre;

    public GrandDomaine getGrandDomaine() {
        return GrandDomaine.valueOf(code.substring(0, 1));
    }
}
