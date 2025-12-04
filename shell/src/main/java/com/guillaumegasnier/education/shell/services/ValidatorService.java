package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.domains.organismes.OrganismeEntity;
import org.springframework.lang.NonNull;

public interface ValidatorService {

    EtablissementEntity toValidEntity(@NonNull EtablissementEntity entity);

    EtablissementOptionEntity toValidEntity(@NonNull EtablissementOptionEntity entity);

    //SectionInternationaleEntity toValidEntity(@NonNull SectionInternationaleEntity entity);

    EtablissementSpecialiteEntity toValidEntity(@NonNull EtablissementSpecialiteEntity entity);

    EtablissementLangueEntity toValidEntity(@NonNull EtablissementLangueEntity entity);

    //SportEtudeEntity toValidEntity(@NonNull SportEtudeEntity entity);

    //SectionSportiveEntity toValidEntity(@NonNull SectionSportiveEntity entity);

    EtablissementSportEntity toValidEntity(@NonNull EtablissementSportEntity entity);

    OrganismeEntity toValidEntity(@NonNull OrganismeEntity entity);
}
