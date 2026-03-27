package com.guillaumegasnier.education.web.dto.references;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class AcademieWithDepartementsDto {

    private AcademieDto academie;
    private List<DepartementDto> departements;

    public AcademieWithDepartementsDto(AcademieDto academie, List<DepartementDto> departements) {
        this.academie = academie;
        this.departements = departements;
    }
}
