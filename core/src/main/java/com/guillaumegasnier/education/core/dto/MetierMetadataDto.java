package com.guillaumegasnier.education.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class MetierMetadataDto implements Serializable {

    // Blocs
    private Set<MetierBlocDto> blocs = new HashSet<>();

    // Appellations
    private Set<MetierAppellationDto> appellations = new HashSet<>();

}
