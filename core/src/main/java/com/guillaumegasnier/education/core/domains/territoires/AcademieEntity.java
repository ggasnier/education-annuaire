package com.guillaumegasnier.education.core.domains.territoires;

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
    @Column(columnDefinition = "BPCHAR(2)", length = 2, unique = true)
    private String code;

    @NotBlank
    @Column(columnDefinition = "VARCHAR(50)", length = 50)
    private String nom;
}
