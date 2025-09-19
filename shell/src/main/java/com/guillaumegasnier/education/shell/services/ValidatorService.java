package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.domains.etablissements.EtablissementEntity;
import org.springframework.lang.NonNull;

public interface ValidatorService {

    EtablissementEntity toValidEntity(@NonNull EtablissementEntity entity);
}
