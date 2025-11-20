package com.guillaumegasnier.education.core.domains.formations;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "formations_specialites")
public class SpecialiteFormationEntity extends AbstractEntity {

    @Id
    @Column(columnDefinition = "BPCHAR(4)", length = 4, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "groupe_code", foreignKey = @ForeignKey(name = "fk_formations_specialites_groupes"))
    private GroupeSpecialiteFormationEntity groupe;

    private String nom;
}
