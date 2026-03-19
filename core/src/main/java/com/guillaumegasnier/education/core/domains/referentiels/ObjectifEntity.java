package com.guillaumegasnier.education.core.domains.referentiels;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rome_objectifs")
public class ObjectifEntity extends AbstractEntity {

    @Id
    @Column(columnDefinition = "BPCHAR(3)", length = 3)
    private String code;

    private String nom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enjeu_code", foreignKey = @ForeignKey(name = "fk_objectifs_enjeux"))
    private EnjeuEntity enjeu;
}
