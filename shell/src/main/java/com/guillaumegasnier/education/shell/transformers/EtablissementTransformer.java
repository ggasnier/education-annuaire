package com.guillaumegasnier.education.shell.transformers;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.shell.dto.*;
import org.springframework.lang.NonNull;

public interface EtablissementTransformer {

    EtablissementJPOEntity toEtablissementJPOEntity(@NonNull JPODTO dto, @NonNull String source);

    EtablissementContactEntity toEtablissementContactEntity(@NonNull ContactDTO dto, @NonNull String source);

    EtablissementLangueEntity toEtablissementLangueEntity(@NonNull LangueDTO dto, @NonNull String source);

    EtablissementOptionEntity toEtablissementOptionEntity(@NonNull OptionDTO dto, @NonNull String source);

    EtablissementSpecialiteEntity toEtablissementSpecialiteEntity(@NonNull SpecialiteDTO dto, @NonNull String source);

    EtablissementSportEntity toEtablissementSportEntity(@NonNull SportDTO dto, @NonNull String source);

    EtablissementIdentifiantEntity toEtablissementIdentifiantEntity(@NonNull IdentifiantDTO dto, @NonNull String source);
}
