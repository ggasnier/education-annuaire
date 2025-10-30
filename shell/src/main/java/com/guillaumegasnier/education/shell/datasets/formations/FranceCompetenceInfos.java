package com.guillaumegasnier.education.shell.datasets.formations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FranceCompetenceInfos {

    @JsonProperty(value = "fc_is_catalog_general")
    private boolean fcIsCatalogGeneral;

    @JsonProperty(value = "fc_is_habilite_rncp")
    private boolean fcIsHabiliteRncp;

    @JsonProperty(value = "fc_is_certificateur")
    private boolean fcIscertificateur;

    @JsonProperty(value = "fc_is_certificateur_siren")
    private boolean fcIscertificateurSiren;

    @JsonProperty(value = "fc_is_partenaire")
    private boolean fcIsPartenaire;

    @JsonProperty(value = "fc_has_partenaire")
    private boolean fcHasPartenaire;
}