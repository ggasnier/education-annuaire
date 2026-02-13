package com.guillaumegasnier.education.shell.datasets.formations;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.*;

@Getter
@Setter
@ToString
public class OnisepFormationDataset implements Dataset {

    // Formation
    @CsvBindByName(column = "Formation (FOR) libellé")
    private String formationNom;
    @CsvBindByName(column = "FOR URL et ID Onisep")
    private String formationUrl;
    @CsvBindByName(column = "FOR indexation domaine web Onisep")
    private String formatioIndexationDomaineWeb;
    @CsvBindByName(column = "FOR type")
    private String formationnomType;
    @CsvBindByName(column = "FOR nature du certificat")
    private String formationNomNatureCertificat;
    @CsvBindByName(column = "FOR URL référentiel")
    private String formationReferentielUrl;
    @CsvBindByName(column = "FOR niveau de sortie")
    private String formationNiveauSortie;
    // Etablissement
    @CsvBindByName(column = "Lieu d'enseignement (ENS) libellé")
    private String etablissementNom;
    @CsvBindByName(column = "ENS URL et ID Onisep")
    private String etablissementUrl;
    @CsvBindByName(column = "ENS code UAI")
    private String etablissementUai;
    @CsvBindByName(column = "ENS statut")
    private String etablissementStatut;
    @CsvBindByName(column = "ENS adresse")
    private String etablissementAdresse;
    @CsvBindByName(column = "ENS code postal")
    private String etablissementCodePostal;
    @CsvBindByName(column = "ENS commune")
    private String etablissementNomCommune;
    // Action de Formation
    @CsvBindByName(column = "Action de Formation (AF) identifiant Onisep")
    private String actionFormationUrl;
    //ENS département
    //ENS académie
    //ENS région
    //ENS latitude
    //ENS longitude
    //ENS n° téléphone
    //ENS site web
    @CsvBindByName(column = "ENS hébergement")
    private String etablissementHebergement;
    @CsvBindByName(column = "ENS accessibilité")
    private String etablissementAccessibilite;
    @CsvBindByName(column = "AF durée cycle standard")
    private String actionFormationDureeCycleStandard;
    @CsvBindByName(column = "AF modalités scolarité")
    private String actionFormationModalitesScolarite;
    @CsvBindByName(column = "AF éléments d'enseignement")
    private String actionFormationElementsEnseignement;
    @CsvBindByName(column = "AF coût scolarité")
    private String actionFormationCoutScolarite;
    @CsvBindByName(column = "AF modalités accueil")
    private String actionFormationModalitesAccueil;
    @CsvBindByName(column = "AF page web")
    private String actionFormationPageWeb;
    @CsvBindByName(column = "AF date de modification")
    private String actionFormationDateModification;

    public String getAccesHandicapes() {
        if (etablissementAccessibilite == null || etablissementAccessibilite.isBlank()) return null;
        if (etablissementAccessibilite.length() > 255) return etablissementAccessibilite.substring(0, 255);
        return etablissementAccessibilite;
    }

    public String getPrixTotalTTC() {
        if (actionFormationCoutScolarite == null || actionFormationCoutScolarite.isBlank()) return null;
        if (actionFormationCoutScolarite.length() > 255) return actionFormationCoutScolarite.substring(0, 255);
        return actionFormationCoutScolarite;
    }

    public String getHebergement() {
        if (etablissementHebergement == null || etablissementHebergement.isBlank()) return null;
        if (etablissementHebergement.length() > 255) return etablissementHebergement.substring(0, 255);
        return etablissementHebergement;
    }

    public Integer getModalitesEnseignement() {
        if (actionFormationModalitesScolarite == null) return null;
        return switch (actionFormationModalitesScolarite) {
            case "temps plein, cours en présentiel" -> 0;
            case "temps plein, cours en distanciel" -> 1;
            default -> null;
        };
    }

    public String getUrlAction() {
        if (actionFormationPageWeb == null || actionFormationPageWeb.isBlank()) return null;
        if (actionFormationPageWeb.length() > 255) return null;
        return actionFormationPageWeb;
    }

    public Integer getDureeCycle() {
        if (actionFormationDureeCycleStandard == null) return null;
        return Integer.parseInt(actionFormationDureeCycleStandard.substring(0, 1));
    }

    /**
     *
     * @return
     */
    public Integer getCodeNiveauSortie() {
        if (formationNiveauSortie == null || formationNiveauSortie.isBlank()) return 0;
        return switch (formationNiveauSortie) {
            case "bac + 9 et plus", "bac + 8", "bac + 7", "bac + 6", "bac + 5" ->
                    8; // niveau I (supérieur à la maîtrise)
            case "bac + 4", "bac + 3" -> 7; // niveau II (licence ou maîtrise universitaire)
            case "bac + 2" -> 6; // niveau III (BTS, DUT)
            case "bac + 1" -> 5; // niveau IV (BP, BT, baccalauréat professionnel ou technologique)
            default -> 0;
        };
    }

    public Boolean isCertifiante() {
        return getCodeCertification() != null;
    }

    /**
     *
     * @return code RNCP
     */
    public String getCodeCertification() {
        return extractRncpCode(formationReferentielUrl);
    }

    public String getActionFormationOnisepId() {
        return Objects.requireNonNull(extractOnisepId(actionFormationUrl)).toString();
    }

    public Integer getEtablissmentOnisepId() {
        return extractOnisepId(etablissementUrl);
    }

    public String getFormationOnisepId() {
        return Objects.requireNonNull(extractOnisepId(formationUrl)).toString();
    }

    public Long getFormationId() {
        return toNormalizedId("FOR", getFormationOnisepId());
    }

    public Long getActionFormationId() {
        return toNormalizedId("AF", getActionFormationOnisepId());
    }
}
