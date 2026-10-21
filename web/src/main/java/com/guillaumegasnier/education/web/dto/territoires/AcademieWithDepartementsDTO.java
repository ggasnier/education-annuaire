package com.guillaumegasnier.education.web.dto.territoires;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "AcademieWithDepartements")
public record AcademieWithDepartementsDTO(AcademieDTO academie,
                                          List<DepartementDTO> departements) {

}
