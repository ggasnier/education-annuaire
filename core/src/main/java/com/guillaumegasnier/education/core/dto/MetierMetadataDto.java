package com.guillaumegasnier.education.core.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MetierMetadataDto {

    // Blocs
    private List<MetierBlocDto> blocs = new ArrayList<>();

    // Appellations
    private List<MetierAppellationDto> appellations = new ArrayList<>();

}
