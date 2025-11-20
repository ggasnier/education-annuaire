package com.guillaumegasnier.education.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guillaumegasnier.education.core.enums.OptionEtablissement;
import com.guillaumegasnier.education.core.enums.SectionInternationale;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Deprecated
public class InformationsDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "sections_sports_etudes", required = true)
    private Set<String> sectionSportsEtudes = new HashSet<>();

    @JsonProperty(value = "sections_internationales")
    private Set<SectionInternationaleDto> sectionsInternationales = new HashSet<>();

    @JsonProperty(required = true)
    private Set<LangueDto> langues = new HashSet<>();

    @JsonProperty(required = true)
    private Set<ContactDto> contacts = new HashSet<>();

    /**
     * Demi pension, etc...
     */
    @JsonProperty(required = true)
    private Set<OptionEtablissement> options = new HashSet<>();

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
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
    @EqualsAndHashCode
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

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public static class SectionInternationaleDto implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        private SectionInternationale section;

        /**
         * 2nde
         * BFI
         * Collège
         */
        private String niveau;
    }
}
