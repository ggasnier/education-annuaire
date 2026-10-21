package com.guillaumegasnier.education.shell.transformers.impl;

import com.guillaumegasnier.education.core.domains.referentiels.*;
import com.guillaumegasnier.education.core.enums.CoeurMetier;
import com.guillaumegasnier.education.core.enums.TypologieDiplome;
import com.guillaumegasnier.education.core.services.CoreReferentielService;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeSavoirDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.SavoirDataset;
import com.guillaumegasnier.education.shell.dto.referentiels.*;
import com.guillaumegasnier.education.shell.mappers.ReferentielMapper;
import com.guillaumegasnier.education.shell.transformers.ReferentielTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
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
    public CertificationNationaleEntity toCertificationNationaleEntity(CertificationDTO dto) {
        Optional<CertificationNationaleEntity> opt = coreReferentielService.findCertification(dto.getCode());
        Optional<CertificationNationaleEntity> opt2 = coreReferentielService.findCertification(dto.getNouveauCode());

        CertificationNationaleEntity entity;

//        if (dto.getUrl() != null && dto.getUrl().length() > 255) {
//            log.info("{}:{}:{}", dto.getCode(), dto.getUrl().length(), dto.getUrl());
//        }

        if (opt.isPresent()) {
            entity = opt.get();
            entity.setNom(dto.getNom());
            entity.setActif(dto.getActif());
            entity.setNouvelleCertification(opt2.orElse(null));
            entity.setEtat(dto.getEtat());
            entity.setTypologieDiplome(TypologieDiplome.valueOf(dto.getTypologieDiplome()));
            entity.setNiveau(dto.getNiveau());
            entity.setActivitesVisees(dto.getActivitesVisees());
            entity.setCapacitesAttestees(dto.getCapacitesAttestees());
            entity.setSecteursActivite(dto.getSecteursActivite());
            entity.setTypeEmploiAccessibles(dto.getTypeEmploiAccessibles());
            entity.setPrerequisEntreeFormation(dto.getPrerequisEntreeFormation());
            entity.setReglementationsActivites(dto.getReglementationsActivites());
            entity.setAccessiblePolynesieFrancaise(dto.getAccessiblePolynesieFrancaise());
            entity.setAccessibleNouvelleCaledonie(dto.getAccessibleNouvelleCaledonie());
            entity.setPublicationDecret(dto.getPublicationDecret());
            entity.setUrl(dto.getUrl());
            entity.setObjectifsContexte(dto.getObjectifsContexte());

            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            entity = referentielMapper.toCertificationNationaleEntity(dto);
        }
        return entity;
    }

    @Override
    public DomaineEntity toDomaineCompetenceEntity(@NonNull DomaineCompetenceDTO dto) {
        Optional<DomaineEntity> opt = coreReferentielService.findDomaine(dto.getCode());

        DomaineEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            entity = new DomaineEntity();
            entity.setCode(dto.getCode());
            entity.setNom(dto.getNom());
        }

        return entity;
    }

    @Override
    public EnjeuEntity toEnjeuEntity(@NonNull EnjeuDTO dto) {
        Optional<EnjeuEntity> opt = coreReferentielService.findEnjeu(dto.getCode());
        Optional<DomaineEntity> opt2 = coreReferentielService.findDomaine(dto.getCodeDomaineCompetence());

        EnjeuEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
            entity.setNom(dto.getNom());
            entity.setDomaine(opt2.orElse(null));
            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            entity = new EnjeuEntity();
            entity.setCode(dto.getCode());
            entity.setNom(dto.getNom());
            entity.setDomaine(opt2.orElse(null));

        }

        return entity;
    }

    @Override
    public ObjectifEntity toObjectifEntity(@NonNull ObjectifDTO dto) {
        Optional<ObjectifEntity> opt = coreReferentielService.findObjectif(dto.getCode());
        Optional<EnjeuEntity> opt2 = coreReferentielService.findEnjeu(dto.getCodeEnjeu());

        ObjectifEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
            entity.setNom(dto.getNom());
            entity.setEnjeu(opt2.orElse(null));
            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            entity = new ObjectifEntity();
            entity.setCode(dto.getCode());
            entity.setNom(dto.getNom());
            entity.setEnjeu(opt2.orElse(null));
        }

        return entity;
    }

    @Override
    public MacroCompetenceEntity toMacroCompetenceEntity(@NonNull MacroCompetenceDTO dto) {
        Optional<MacroCompetenceEntity> opt = coreReferentielService.findMacroCompetence(dto.getCode());
        Optional<ObjectifEntity> opt2 = coreReferentielService.findObjectif(dto.getCodeObjectif());

        MacroCompetenceEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
            entity.setNom(dto.getNom());
            entity.setObjectif(opt2.orElse(null));
            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            entity = new MacroCompetenceEntity();
            entity.setCode(dto.getCode());
            entity.setNom(dto.getNom());
            entity.setObjectif(opt2.orElse(null));
        }

        return entity;
    }

    @Override
    public CompetenceEntity toCompetenceEntity(@NonNull CompetenceDTO dto) {

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

    @Override
    public MetierSavoirEntity toMetierSavoirEntity(@NonNull SavoirDataset savoirDataset) {
        Optional<MetierSavoirEntity> opt = coreReferentielService.findSavoir(savoirDataset.getCodeOgr());

        MetierSavoirEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            entity = new MetierSavoirEntity();
            entity.setCode(savoirDataset.getCodeOgr());
            entity.setNom(savoirDataset.getLibelle());
            entity.setCodeRubrique(savoirDataset.getCodeRubrique());
            entity.setCategorie(savoirDataset.getCategorie());
            entity.setSousCategorie(savoirDataset.getSousCategorie());
            entity.setTransitionEco(savoirDataset.getTransitionEco());
        }

        return entity;
    }


    @Override
    public MetierSavoirLienEntity toMetierSavoirLienEntity(@NonNull RomeSavoirDataset romeSavoirDataset) {

        log.debug("Création du lien savoir-metier : {} - {}", romeSavoirDataset.getCodeRome(), romeSavoirDataset.getCodeOgr());

        Optional<MetierEntity> opt1 = coreReferentielService.findMetier(romeSavoirDataset.getCodeRome());
        Optional<MetierSavoirEntity> opt2 = coreReferentielService.findSavoir(romeSavoirDataset.getCodeOgr());

        if (opt1.isPresent() && opt2.isPresent()) {

            Optional<MetierSavoirLienEntity> opt3 = coreReferentielService.findMetierSavoirLien(opt1.get().getCode(), opt2.get().getCode());

            if (opt3.isPresent()) {
                return opt3.get();
            }
            MetierSavoirLienEntity entity = new MetierSavoirLienEntity();
            entity.setMetier(opt1.get());
            entity.setSavoir(opt2.get());
            entity.setCoeurMetier(romeSavoirDataset.getCoeurMetier());
            return entity;
        } else {
            log.debug("Lien savoir-metier non créé : metier {} ou savoir {} introuvable", romeSavoirDataset.getCodeRome(), romeSavoirDataset.getCodeOgr());
        }

        return null;
    }

    @Override
    public MetierCompetenceEntity toMetierCompetenceEntity(@NonNull RomeSavoirDataset romeSavoirDataset) {
        Optional<MetierEntity> opt1 = coreReferentielService.findMetier(romeSavoirDataset.getCodeRome());
        Optional<CompetenceEntity> opt2 = coreReferentielService.findCompetence(romeSavoirDataset.getCodeOgr());

        if (opt1.isPresent() && opt2.isPresent()) {

            Optional<MetierCompetenceEntity> opt3 = coreReferentielService.findMetierCompetence(opt1.get().getCode(), opt2.get().getCode());

            if (opt3.isPresent()) {
                return opt3.get();
            }
            MetierCompetenceEntity entity = new MetierCompetenceEntity();
            entity.setMetier(opt1.get());
            entity.setCompetence(opt2.get());
            entity.setCoeurMetier(CoeurMetier.valueOf(romeSavoirDataset.getCoeurMetier()));
            return entity;
        } else {
            log.debug("Lien competence-metier non créé : metier {} ou competence {} introuvable", romeSavoirDataset.getCodeRome(), romeSavoirDataset.getCodeOgr());
        }

        return null;
    }
}
