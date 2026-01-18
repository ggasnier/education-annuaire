package com.guillaumegasnier.education.shell.datasets.formations;

import com.guillaumegasnier.education.shell.datasets.Dataset;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

import static com.guillaumegasnier.education.shell.utils.ShellUtil.extractOnisepId;
import static com.guillaumegasnier.education.shell.utils.ShellUtil.extractRncpCode;

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
    //ENS hébergement
    //ENS accessibilité
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

    public Integer getActionFormationOnisepId() {
        return extractOnisepId(actionFormationUrl);
    }

    public Integer getEtablissmentOnisepId() {
        return extractOnisepId(etablissementUrl);
    }

    public Integer getFormationOnisepId() {
        return extractOnisepId(formationUrl);
    }

    public UUID getFormationId() {
        return UUID.nameUUIDFromBytes(("FOR" + getFormationOnisepId()).getBytes());
    }

    public UUID getActionFormationId() {
        return UUID.nameUUIDFromBytes(("AF" + getActionFormationOnisepId()).getBytes());
    }
}
