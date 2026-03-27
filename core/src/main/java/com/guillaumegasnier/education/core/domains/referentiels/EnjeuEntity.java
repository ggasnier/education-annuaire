package com.guillaumegasnier.education.core.domains.referentiels;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rome_enjeux")
public class EnjeuEntity extends AbstractEntity {

    @Id
    @Column(columnDefinition = "BPCHAR(2)", length = 2)
    private String code;

    private String nom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domaine_code", foreignKey = @ForeignKey(name = "fk_enjeux_domaines"))
    private DomaineEntity domaine;
}
