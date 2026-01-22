package com.guillaumegasnier.education.shell.transformers.impl;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreTerritoireService;
import com.guillaumegasnier.education.core.validations.etablissements.Effectifs;
import com.guillaumegasnier.education.core.validations.etablissements.IndicateurValeurAjoutee;
import com.guillaumegasnier.education.core.validations.etablissements.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import com.guillaumegasnier.education.shell.datasets.etablissements.EtablissementDataset;
import com.guillaumegasnier.education.shell.datasets.etablissements.JPODataset;
import com.guillaumegasnier.education.shell.dto.etablissements.*;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.transformers.EtablissementTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EtablissementTransformerImpl implements EtablissementTransformer {

    private final CoreEtablissementService coreEtablissementService;
    private final CoreTerritoireService coreTerritoireService;
    private final EtablissementMapper etablissementMapper;

    @Override
    public <T extends IndicePositionSociale & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(@NonNull T dataset) {
        var uai = dataset.getUai();
        var annee = dataset.getAnnee();

        if (coreEtablissementService.isEtablissementExiste(uai)) {
            Optional<EtablissementMetadataEntity> metadataEntityOptional = coreEtablissementService.findMetadata(uai, annee);

            if (metadataEntityOptional.isPresent()) {
                var entity = metadataEntityOptional.get();
                var metadatas = entity.getMetadatas();
                metadatas.setIps(etablissementMapper.toIndicePositionSocialeDto(dataset));
                entity.setMetadatas(metadatas);
                return entity;
            } else {
                var entity = new EtablissementMetadataEntity(new EtablissementAnneePK(annee, uai), coreEtablissementService.getEtablissementReferenceByUai(uai));
                var metadatas = entity.getMetadatas();
                metadatas.setIps(etablissementMapper.toIndicePositionSocialeDto(dataset));
                entity.setMetadatas(metadatas);
                return entity;
            }
        } else {
            log.warn("Etablissement {} non trouvé pour IPS {}", uai, annee);
            return null;
        }
    }

    @Override
    public <T extends Effectifs & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(@NonNull T dataset) {
        var uai = dataset.getUai();
        var annee = dataset.getAnnee();

        if (coreEtablissementService.isEtablissementExiste(uai)) {
            Optional<EtablissementMetadataEntity> metadataEntityOptional = coreEtablissementService.findMetadata(uai, annee);

            if (metadataEntityOptional.isPresent()) {
                var entity = metadataEntityOptional.get();
                var metadatas = entity.getMetadatas();
                if (dataset.getEffectifs() != null) {
                    metadatas.setEffectifs(dataset.getEffectifs());
                }
                entity.setMetadatas(metadatas);
                return entity;
            } else {
                var entity = new EtablissementMetadataEntity(new EtablissementAnneePK(annee, uai), coreEtablissementService.getEtablissementReferenceByUai(uai));
                var metadatas = entity.getMetadatas();
                if (dataset.getEffectifs() != null) {
                    metadatas.setEffectifs(dataset.getEffectifs());
                }
                entity.setMetadatas(metadatas);
                return entity;
            }
        } else {
            log.warn("Pas d'établissement avec UAI {} pour effectifs", uai);
            return null;
        }
    }

    @Override
    public <T extends IndicateurValeurAjoutee & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(@NonNull T dataset) {
        var uai = dataset.getUai();
        var annee = dataset.getAnnee();

        if (coreEtablissementService.isEtablissementExiste(uai)) {
            Optional<EtablissementMetadataEntity> metadataEntityOptional = coreEtablissementService.findMetadata(uai, annee);

            if (metadataEntityOptional.isPresent()) {
                var entity = metadataEntityOptional.get();
                var metadatas = entity.getMetadatas();
                metadatas.setIva(etablissementMapper.toIndicateurValeurAjouteeDto(dataset));
                entity.setMetadatas(metadatas);
                return entity;
            } else {
                var entity = new EtablissementMetadataEntity(new EtablissementAnneePK(annee, uai), coreEtablissementService.getEtablissementReferenceByUai(uai));
                var metadatas = entity.getMetadatas();
                metadatas.setIva(etablissementMapper.toIndicateurValeurAjouteeDto(dataset));
                entity.setMetadatas(metadatas);
                return entity;
            }
        } else {
            log.warn("Etablissement {} non trouvé pour IVA {}", uai, annee);
            return null;
        }
    }

    @Override
    public <T extends EtablissementDataset> EtablissementEntity toEtablissementEntity(@NonNull T dataset, @NonNull String source) {
        return coreEtablissementService.findEtablissement(dataset.getUai())
                .map(entity -> toEtablissementEntityOld(entity, dataset, source))
                .orElseGet(() -> toEtablissementEntityNew(dataset, source));
    }

    <T extends EtablissementDataset> EtablissementEntity toEtablissementEntityOld(@NonNull EtablissementEntity entity, @NonNull T dataset, @NonNull String source) {
        // Ne mettre à jour les champs que s'ils sont renseignés

        // Le code commune
        setCodeComumne(entity, dataset.getCodeCommune(), dataset.getNomCommune());
        // Le code nature
        setCodeNature(entity, dataset.getCodeNature());
        // Le code contrat pour le privé
        setCodeContrat(entity, dataset.getCodeContrat());
        //Les sources de données
        entity.addSource(source);

        if (dataset.getDateOuverture() != null)
            entity.setDateOuverture(dataset.getDateOuverture());

        if (dataset.getDateFermeture() != null)
            entity.setDateFermeture(dataset.getDateFermeture());

        entity.setUpdatedAt(LocalDateTime.now());

        if (dataset.isActif() != null) {
            entity.setActif(dataset.isActif());
        }

        entity.addSource(source);

        return entity;
    }

    <T extends EtablissementDataset> EtablissementEntity toEtablissementEntityNew(@NonNull T dataset, @NonNull String source) {
        EtablissementEntity entity = etablissementMapper.toEntity(dataset);
        // Le code commune
        setCodeComumne(entity, dataset.getCodeCommune(), dataset.getNomCommune());
        // Le code nature
        setCodeNature(entity, dataset.getCodeNature());
        // Le code contrat pour le privé
        setCodeContrat(entity, dataset.getCodeContrat());
        //Les sources de données
        entity.addSource(source);

        return entity;
    }

    void setCodeContrat(EtablissementEntity entity, String codeContrat) {
        if (codeContrat != null && entity.getContrat() == null)
            coreEtablissementService.findContrat(codeContrat).ifPresent(entity::setContrat);
    }

    void setCodeNature(EtablissementEntity entity, String codeNature) {
        if (codeNature != null && entity.getNature().getCode().equals("$"))
            coreEtablissementService.findNature(codeNature).ifPresent(entity::setNature);
    }

    void setCodeComumne(@NonNull EtablissementEntity entity, String codeCommune, String nomCommune) {
        if (entity.getCommune() == null) {
            if (codeCommune != null && !codeCommune.isBlank()) {
                var communeOptional = coreTerritoireService.findCommune(codeCommune);
                if (communeOptional.isPresent()) {
                    entity.setCommune(communeOptional.get());
                } else {
                    communeOptional = coreTerritoireService.findCommuneByNom(nomCommune);
                    if (communeOptional.isPresent()) {
                        entity.setCommune(communeOptional.get());
                    } else {
                        log.warn("Commune inconnue pour {} / {}", codeCommune, nomCommune);
                    }
                }
            } else if (nomCommune != null && !nomCommune.isBlank()) {
                var communeOptional = coreTerritoireService.findCommuneByNom(nomCommune);

                if (communeOptional.isPresent()) {
                    entity.setCommune(communeOptional.get());
                } else {
                    log.warn("Commune absente pour {}", nomCommune);
                }
            }
        }
    }

    @Override
    public EtablissementJPOEntity toEtablissementJPOEntity(@NonNull JPODataset dto, @NonNull String source) {
        Optional<EtablissementJPOEntity> opt = coreEtablissementService.findJPO(dto.getUai(), dto.getDateDebut(), dto.getDateFin());
        EtablissementJPOEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
        } else {
            if (coreEtablissementService.isEtablissementExiste(dto.getUai())) {
                entity = new EtablissementJPOEntity();
                entity.setPk(new EtablissementJPOPK(dto.getUai(), dto.getDateDebut(), dto.getDateFin()));
                entity.setHeureFin(dto.getHeureDebut());
                entity.setHeureFin(dto.getHeureFin());
                entity.setCommentaire(dto.getCommentaire());
                entity.setEtablissement(coreEtablissementService.getEtablissementReferenceByUai(dto.getUai()));
            } else {
                log.warn("Etablissement {} non trouvé pour JPO {} & {}", dto.getUai(), dto.getDateDebut(), dto.getDateFin());
                return null;
            }
        }

        entity.addSource(source);
        return entity;
    }

    @Override
    public EtablissementLangueEntity toEtablissementLangueEntity(@NonNull LangueDTO dto, @NonNull String source) {
        Optional<EtablissementLangueEntity> opt = coreEtablissementService.findLangue(dto.uai(), dto.langue(), dto.categorie(), dto.enseignement());
        EtablissementLangueEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
        } else {
            if (coreEtablissementService.isEtablissementExiste(dto.uai())) {
                entity = new EtablissementLangueEntity(new EtablissementLanguePK(dto.uai(), dto.langue(), dto.categorie(), dto.enseignement()), coreEtablissementService.getEtablissementReferenceByUai(dto.uai()));
            } else {
                log.warn("Etablissement {} non trouvé pour langues {} & {} & {}", dto.uai(), dto.langue(), dto.categorie(), dto.enseignement());
                return null;
            }
        }

        entity.addSource(source);
        return entity;
    }

    @Override
    public EtablissementOptionEntity toEtablissementOptionEntity(@NonNull OptionDTO dto, @NonNull String source) {
        Optional<EtablissementOptionEntity> opt = coreEtablissementService.findOption(dto.uai(), dto.option());
        EtablissementOptionEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
        } else {
            if (coreEtablissementService.isEtablissementExiste(dto.uai())) {
                entity = new EtablissementOptionEntity(new EtablissementOptionPK(dto.uai(), dto.option()), coreEtablissementService.getEtablissementReferenceByUai(dto.uai()));
            } else {
                log.warn("Etablissement {} non trouvé pour options {}", dto.uai(), dto.option());
                return null;
            }
        }

        entity.addSource(source);
        return entity;
    }

    @Override
    public EtablissementSportEntity toEtablissementSportEntity(@NonNull SportDTO dto, @NonNull String source) {
        Optional<EtablissementSportEntity> opt = coreEtablissementService.findSport(dto.uai(), dto.sport(), dto.categorie());
        EtablissementSportEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
        } else {
            if (coreEtablissementService.isEtablissementExiste(dto.uai())) {
                entity = new EtablissementSportEntity(dto.uai(), dto.sport(), dto.categorie(), coreEtablissementService.getEtablissementReferenceByUai(dto.uai()));
            } else {
                log.warn("Etablissement {} non trouvé pour sports {} & {}", dto.uai(), dto.sport(), dto.categorie());
                return null;
            }
        }

        entity.addSource(source);
        return entity;
    }

    @Override
    public EtablissementSpecialiteEntity toEtablissementSpecialiteEntity(@NonNull SpecialiteDTO dto, @NonNull String source) {
        Optional<EtablissementSpecialiteEntity> opt = coreEtablissementService.findSpecialite(dto.uai(), dto.specialite());
        EtablissementSpecialiteEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
        } else {
            if (coreEtablissementService.isEtablissementExiste(dto.uai())) {
                var pk = new EtablissementSpecialitePK(dto.uai(), dto.specialite());
                entity = new EtablissementSpecialiteEntity(pk, coreEtablissementService.getEtablissementReferenceByUai(dto.uai()));
            } else {
                log.warn("Etablissement {} non trouvé pour specialites {}", dto.uai(), dto.specialite());
                return null;
            }
        }

        return entity;
    }

    @Override
    public EtablissementMasaEntity toEtablissementMasaEntity(@NonNull MasaDTO dto) {
        Optional<EtablissementMasaEntity> opt = coreEtablissementService.findMasa(dto.masaId());
        EtablissementMasaEntity entity;

        if (opt.isPresent()) {
            entity = opt.get();
        } else {
            if (coreEtablissementService.isEtablissementExiste(dto.uai())) {
                entity = new EtablissementMasaEntity(dto.masaId(), coreEtablissementService.getEtablissementReferenceByUai(dto.uai()));
            } else {
                log.warn("Etablissement {} non trouvé pour masa {}", dto.uai(), dto.masaId());
                return null;
            }
        }

        return entity;
    }

    @Override
    public EtablissementContactEntity toEtablissementContactEntity(@NonNull ContactDTO dto, @NonNull String source) {
        return null;
    }

    @Override
    public JPODataset finUai(@NonNull JPODataset dataset) {
        Optional<EtablissementMasaEntity> opt = coreEtablissementService.findMasa(dataset.getUai());

        if (opt.isPresent()) {
            dataset.setUai(opt.get().getEtablissement().getUai());
            // TODO : les heures ?
            return dataset;
        } else {
            log.warn("Pas d'établissement avec le code masaId {}", dataset.getUai());
            return null;
        }
    }
}
