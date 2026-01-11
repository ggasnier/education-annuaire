package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import org.springframework.lang.NonNull;

public interface ValidatorService {

    EtablissementEntity toValidEntity(@NonNull EtablissementEntity entity);

    EtablissementOptionEntity toValidEntity(@NonNull EtablissementOptionEntity entity);

    EtablissementContactEntity toValidEntity(@NonNull EtablissementContactEntity entity);

    EtablissementJPOEntity toValidEntity(@NonNull EtablissementJPOEntity entity);

    EtablissementSpecialiteEntity toValidEntity(@NonNull EtablissementSpecialiteEntity entity);

    EtablissementLangueEntity toValidEntity(@NonNull EtablissementLangueEntity entity);
    
    EtablissementSportEntity toValidEntity(@NonNull EtablissementSportEntity entity);

    OrganismeEntity toValidEntity(@NonNull OrganismeEntity entity);
}
