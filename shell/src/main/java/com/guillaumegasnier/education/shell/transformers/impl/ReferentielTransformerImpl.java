package com.guillaumegasnier.education.shell.transformers.impl;

import com.guillaumegasnier.education.core.domains.referentiels.CompetenceEntity;
import com.guillaumegasnier.education.core.domains.referentiels.MacroCompetenceEntity;
import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;
import com.guillaumegasnier.education.core.services.CoreReferentielService;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeDataset;
import com.guillaumegasnier.education.shell.dto.referentiels.CompetenceDTO;
import com.guillaumegasnier.education.shell.mappers.ReferentielMapper;
import com.guillaumegasnier.education.shell.transformers.ReferentielTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
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
            entity.setTransitionEco(dataset.getTransitionEco());
            entity.setTransitionNum(dataset.getTransitionNum());
            entity.setTransitionDemo(dataset.getTransitionDemo());
            entity.setEmploiReglemente(dataset.getEmploiReglemente());
            entity.setEmploiCadre(dataset.getEmploiCadre());
            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            entity = referentielMapper.toMetierEntity(dataset);
        }

        opt2.ifPresent(entity::setMetierParent);

        return entity;
    }

    @Override
    public CompetenceEntity toCompetenceEntity(CompetenceDTO dto) {

        Optional<CompetenceEntity> opt = coreReferentielService.findCompetence(dto.getCode());
        Optional<MacroCompetenceEntity> opt2 = coreReferentielService.findMacroCompetence(dto.getCodeMacroCompetence());

        CompetenceEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
            entity.setMacroCompetence(opt2.orElse(null));
            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            entity = new CompetenceEntity(dto.getCode(), dto.getNom(), opt2.orElse(null));
        }

        return entity;
    }
}
