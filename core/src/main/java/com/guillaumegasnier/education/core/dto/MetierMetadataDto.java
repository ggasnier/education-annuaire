package com.guillaumegasnier.education.core.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class MetierMetadataDto {

    // Blocs
    private Set<MetierBlocDto> blocs = new HashSet<>();

    // Appellations
    private Set<MetierAppellationDto> appellations = new HashSet<>();

}
