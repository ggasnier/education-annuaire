package com.guillaumegasnier.education.core.references.dto;

import com.guillaumegasnier.education.core.references.entities.DepartementEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link DepartementEntity}
 */
@Getter
@Setter
public class DepartementDto implements Serializable {

    @NotNull
    @Size(min = 2, max = 3)
    @NotEmpty
    @NotBlank
    String code;

    @NotNull
    @NotEmpty
    @NotBlank
    String nom;

    String codeRegion;

    String codeAcademie;
}