package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.organismes.OrganismeEntity;
import com.guillaumegasnier.education.core.validations.Effectifs;
import com.guillaumegasnier.education.core.validations.IndicateurValeurAjoutee;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.ParcoursupFormationDataset;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShellEntityService {

    <T extends IndicePositionSociale & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(T dataset);

    <T extends Effectifs & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(T dataset);

    <T extends IndicateurValeurAjoutee & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(T dataset);

    <T extends EtablissementDataset> EtablissementEntity toEtablissementEntity(@NonNull T dataset, String source);

    List<OptionEtablissementEntity> toOptionEtablissementEntity(@NonNull EtablissementDataset dataset);

    OptionEtablissementEntity toOptionEtablissementEntity(@NonNull OnisepDispositifDataset dataset);

    OptionEtablissementEntity toOptionEtablissementEntity(@NonNull EuroscolDataset dataset);

    //List<SectionSportiveEntity> toSectionSportiveEntity(@NonNull SectionSportiveDataset dataset);

    OptionEtablissementEntity toOptionEtablissementEntity(@NonNull SectionBinationaleDataset dataset);

    List<OptionEtablissementEntity> toOptionEtablissementEntity(@NonNull SectionInternationaleDataset dataset);

    //List<SectionInternationaleEntity> toSectionInternationaleEntity(@NonNull SectionInternationaleDataset dataset);

//    IndicePositionSocialeEntity toIndicePositionSocialeEntity(@NonNull IPSDataset dataset, @NonNull String categorie);

    List<SpecialiteEntity> toSpecialiteEntity(@NonNull SpecialitePremiereDataset dataset);

    //SportEtudeEntity toSportEtudeEntity(@NonNull SectionSportEtudeDataset dataset);

    LangueEntity toLangueEntity(@NonNull LangueDataset dataset);

    FormationEntity findFormationByOnisepId(OnisepFormationDataset dataset);

    OrganismeEntity toOrganismeEntity(@NonNull TravailOrganismeFormationDataset dataset);

    List<EtablissementSportEntity> toEtablissementSportEntity(SportDataset dataset, String categorie);

    List<LangueEntity> toLangueEntity(@NonNull OnisepDispositifDataset dataset, String enseigenement);

    ActionFormationEntity toActionFormationEntity(@NonNull ParcoursupFormationDataset parcoursupFormationDataset, @NonNull FormationEntity entity);
}
