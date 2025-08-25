package com.guillaumegasnier.education.core.domains.references;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "academies")
public class AcademieEntity extends AbstractEntity {

    @Id
    @Column(columnDefinition = "CHAR(2)", length = 2, unique = true)
    private String code;

    @NotBlank
    private String nom;
}
