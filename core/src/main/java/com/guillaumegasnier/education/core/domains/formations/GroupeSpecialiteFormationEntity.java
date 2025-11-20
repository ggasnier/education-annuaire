package com.guillaumegasnier.education.core.domains.formations;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "formations_specialites_groupes")
public class GroupeSpecialiteFormationEntity extends AbstractEntity {

    @Id
    @Column(columnDefinition = "BPCHAR(3)", length = 3, unique = true)
    private String code;

    private String nom;
}
