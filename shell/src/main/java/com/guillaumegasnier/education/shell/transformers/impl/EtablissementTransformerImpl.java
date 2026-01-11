package com.guillaumegasnier.education.shell.transformers.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.shell.dto.*;
import com.guillaumegasnier.education.shell.transformers.EtablissementTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EtablissementTransformerImpl implements EtablissementTransformer {

    private final CoreEtablissementService coreEtablissementService;

    @Override
    public EtablissementJPOEntity toEtablissementJPOEntity(@NonNull JPODTO dto, @NonNull String source) {
        return null;
    }

    @Override
    public EtablissementContactEntity toEtablissementContactEntity(@NonNull ContactDTO dto, @NonNull String source) {
        return null;
    }

    @Override
    public EtablissementLangueEntity toEtablissementLangueEntity(@NonNull LangueDTO dto, @NonNull String source) {
        return null;
    }

    @Override
    public EtablissementOptionEntity toEtablissementOptionEntity(@NonNull OptionDTO dto, @NonNull String source) {
        return null;
    }

    @Override
    public EtablissementSpecialiteEntity toEtablissementSpecialiteEntity(@NonNull SpecialiteDTO dto, @NonNull String source) {
        return null;
    }
    
    @Override
    public EtablissementSportEntity toEtablissementSportEntity(@NonNull SportDTO dto, @NonNull String source) {
        return null;
    }

    @Override
    public EtablissementIdentifiantEntity toEtablissementIdentifiantEntity(@NonNull IdentifiantDTO dto, @NonNull String source) {
        return null;
    }

}
