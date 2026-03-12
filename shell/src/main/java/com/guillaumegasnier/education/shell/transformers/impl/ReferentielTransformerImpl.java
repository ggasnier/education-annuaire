package com.guillaumegasnier.education.shell.transformers.impl;

import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;
import com.guillaumegasnier.education.core.services.CoreReferentielService;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeDataset;
import com.guillaumegasnier.education.shell.mappers.ReferentielMapper;
import com.guillaumegasnier.education.shell.transformers.ReferentielTransformer;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReferentielTransformerImpl implements ReferentielTransformer {

    private CoreReferentielService coreReferentielService;
    private ReferentielMapper referentielMapper;

    @Override
    public MetierEntity toMetierEntity(@NonNull RomeDataset dataset) {

        Optional<MetierEntity> opt = coreReferentielService.findMetier(dataset.getCodeRome());
        Optional<MetierEntity> opt2 = coreReferentielService.findMetier(dataset.getCodeRomeParent());

        MetierEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            entity = referentielMapper.toMetierEntity(dataset);
        }

        opt2.ifPresent(entity::setMetierParent);

        return entity;
    }
}
