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
 * Pour les établissements
 *
 * @deprecated à déplacer dans EtablissementEntityService et FormationEntityService
 */
@Deprecated
public interface ShellEntityService {

    <T extends IndicePositionSociale & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(T dataset);

    <T extends Effectifs & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(T dataset);

    <T extends IndicateurValeurAjoutee & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(T dataset);

    <T extends EtablissementDataset> EtablissementEntity toEtablissementEntity(@NonNull T dataset, String source);

    List<EtablissementOptionEntity> toEtablissementOptionEntity(@NonNull EtablissementDataset dataset, @NonNull String source);

    List<EtablissementContactEntity> toEtablissementContactEntity(@NonNull EtablissementDataset dataset, @NonNull String source);

    List<EtablissementJPOEntity> toEtablissementJPOEntity(@NonNull EtablissementDataset dataset, @NonNull String source);

    EtablissementOptionEntity toEtablissementOptionEntity(@NonNull OnisepDispositifDataset dataset, @NonNull String source);

    EtablissementOptionEntity toEtablissementOptionEntity(@NonNull EuroscolDataset dataset, @NonNull String source);

    EtablissementOptionEntity toEtablissementOptionEntity(@NonNull SectionBinationaleDataset dataset, @NonNull String source);

    List<EtablissementOptionEntity> toEtablissementOptionEntity(@NonNull SectionInternationaleDataset dataset, @NonNull String source);

    List<EtablissementSpecialiteEntity> toSpecialiteEntity(@NonNull SpecialitePremiereDataset dataset, @NonNull String source);

    EtablissementLangueEntity toLangueEntity(@NonNull LangueDataset dataset, @NonNull String source);

    /**
     * voir {@link com.guillaumegasnier.education.shell.transformers.EtablissementTransformer}
     *
     * @param dataset
     * @return
     */
    @Deprecated
    FormationEntity findFormationByOnisepId(OnisepFormationDataset dataset);

    OrganismeEntity toOrganismeEntity(@NonNull TravailOrganismeFormationDataset dataset);

    List<EtablissementSportEntity> toEtablissementSportEntity(@NonNull SportDataset dataset, @NonNull Sport.Categorie categorie, @NonNull String source);

    List<EtablissementLangueEntity> toLangueEntity(@NonNull OnisepDispositifDataset dataset, @NonNull Langue.Categorie categorie, @NonNull String source);

    @Deprecated
    ActionFormationEntity toActionFormationEntity(@NonNull ParcoursupFormationDataset parcoursupFormationDataset, @NonNull FormationEntity entity);
}
