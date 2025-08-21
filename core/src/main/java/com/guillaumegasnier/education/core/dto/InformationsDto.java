package com.guillaumegasnier.education.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class InformationsDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "sections_sportives", required = true)
    private List<String> sectionsSportives = new ArrayList<>();

    @JsonProperty(value = "sections_sports_etudes", required = true)
    private List<String> sectionSportsEtudes = new ArrayList<>();

    @JsonProperty(required = true)
    private List<LangueDto> langues = new ArrayList<>();

    @JsonProperty(required = true)
    private Set<ContactDto> contacts = new HashSet<>();

    /**
     * Demi pension, etc...
     */
    @JsonProperty(required = true)
    private Set<String> options = new HashSet<>();

    @Getter
    @Setter
    @ToString
    public static class LangueDto implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        // anglais, allemand, espagnol, etc...
        private String langue;

        // lv1, lv2, lca
        private String enseignement;
    }

    @Getter
    @Setter
    @ToString
    public static class ContactDto implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @NotNull
        @NotBlank
        private String clef;

        @NotNull
        @NotBlank
        private String valeur;

    }
}
