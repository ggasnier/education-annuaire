package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import org.springframework.lang.NonNull;

public interface ValidatorService {

    EtablissementEntity toValidEntity(@NonNull EtablissementEntity entity);

    OptionEtablissementEntity toValidEntity(@NonNull OptionEtablissementEntity entity);

//    IndicePositionSocialeEntity toValidEntity(@NonNull IndicePositionSocialeEntity entity);

    SectionInternationaleEntity toValidEntity(@NonNull SectionInternationaleEntity entity);

    SpecialiteEntity toValidEntity(@NonNull SpecialiteEntity entity);

    LangueEntity toValidEntity(@NonNull LangueEntity entity);

    SportEtudeEntity toValidEntity(@NonNull SportEtudeEntity entity);

    SectionSportiveEntity toValidEntity(@NonNull SectionSportiveEntity entity);

    OrganismeEntity toValidEntity(@NonNull OrganismeEntity entity);
}
