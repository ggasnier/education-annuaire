package com.guillaumegasnier.education.shell.transformers;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.validations.etablissements.Effectifs;
import com.guillaumegasnier.education.core.validations.etablissements.IndicateurValeurAjoutee;
import com.guillaumegasnier.education.core.validations.etablissements.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.guillaumegasnier.education.shell.datasets.etablissements.EtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.JPODataset;
import com.guillaumegasnier.education.shell.dto.etablissements.*;
import org.springframework.lang.NonNull;

public interface EtablissementTransformer {

    <T extends IndicePositionSociale & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(T dataset);

    <T extends Effectifs & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(T dataset);

    <T extends IndicateurValeurAjoutee & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(T dataset);

    <T extends EtablissementDataset> EtablissementEntity toEtablissementEntity(@NonNull T dataset, @NonNull String source);

    EtablissementJPOEntity toEtablissementJPOEntity(@NonNull JPODataset dto, @NonNull String source);

    EtablissementContactEntity toEtablissementContactEntity(@NonNull ContactDTO dto, @NonNull String source);

    EtablissementLangueEntity toEtablissementLangueEntity(@NonNull LangueDTO dto, @NonNull String source);

    EtablissementOptionEntity toEtablissementOptionEntity(@NonNull OptionDTO dto, @NonNull String source);

    EtablissementSpecialiteEntity toEtablissementSpecialiteEntity(@NonNull SpecialiteDTO dto, @NonNull String source);

    EtablissementSportEntity toEtablissementSportEntity(@NonNull SportDTO dto, @NonNull String source);

    EtablissementIdentifiantEntity toEtablissementIdentifiantEntity(@NonNull IdentifiantDTO dto, @NonNull String source);

    EtablissementMasaEntity toEtablissementMasaEntity(@NonNull MasaDTO dto);
}
