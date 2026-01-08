package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.enums.Langue;
import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.validations.Effectifs;
import com.guillaumegasnier.education.core.validations.IndicateurValeurAjoutee;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.ParcoursupFormationDataset;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Pour les établissemen
 */
public interface ShellEntityService {

    <T extends IndicePositionSociale & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(T dataset);

    <T extends Effectifs & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(T dataset);

    <T extends IndicateurValeurAjoutee & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(T dataset);

    <T extends EtablissementDataset> EtablissementEntity toEtablissementEntity(@NonNull T dataset, String source);

    List<EtablissementOptionEntity> toEtablissementOptionEntity(@NonNull EtablissementDataset dataset);

    List<EtablissementContactEntity> toEtablissementContactEntity(@NonNull EtablissementDataset dataset);

    List<EtablissementJPOEntity> toEtablissementJPOEntity(@NonNull EtablissementDataset dataset);

    EtablissementOptionEntity toEtablissementOptionEntity(@NonNull OnisepDispositifDataset dataset);

    EtablissementOptionEntity toEtablissementOptionEntity(@NonNull EuroscolDataset dataset);

    //List<SectionSportiveEntity> toSectionSportiveEntity(@NonNull SectionSportiveDataset dataset);

    EtablissementOptionEntity toEtablissementOptionEntity(@NonNull SectionBinationaleDataset dataset);

    List<EtablissementOptionEntity> toEtablissementOptionEntity(@NonNull SectionInternationaleDataset dataset);

    //List<SectionInternationaleEntity> toSectionInternationaleEntity(@NonNull SectionInternationaleDataset dataset);

//    IndicePositionSocialeEntity toIndicePositionSocialeEntity(@NonNull IPSDataset dataset, @NonNull String categorie);

    List<EtablissementSpecialiteEntity> toSpecialiteEntity(@NonNull SpecialitePremiereDataset dataset);

    //SportEtudeEntity toSportEtudeEntity(@NonNull SectionSportEtudeDataset dataset);

    EtablissementLangueEntity toLangueEntity(@NonNull LangueDataset dataset);

    FormationEntity findFormationByOnisepId(OnisepFormationDataset dataset);

    OrganismeEntity toOrganismeEntity(@NonNull TravailOrganismeFormationDataset dataset);

    List<EtablissementSportEntity> toEtablissementSportEntity(@NonNull SportDataset dataset, @NonNull Sport.Categorie categorie);

    List<EtablissementLangueEntity> toLangueEntity(@NonNull OnisepDispositifDataset dataset, @NonNull Langue.Categorie categorie);

    ActionFormationEntity toActionFormationEntity(@NonNull ParcoursupFormationDataset parcoursupFormationDataset, @NonNull FormationEntity entity);
}
