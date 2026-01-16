package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.core.services.CoreEtablissementService;
import com.guillaumegasnier.education.core.services.CoreFormationService;
import com.guillaumegasnier.education.core.services.CoreReferenceService;
import com.guillaumegasnier.education.shell.mappers.EtablissementMapper;
import com.guillaumegasnier.education.shell.mappers.FormationMapper;
import com.guillaumegasnier.education.shell.services.ShellEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Deprecated
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ShellEntityServiceImpl implements ShellEntityService {

    private final CoreReferenceService coreReferenceService;
    private final CoreEtablissementService coreEtablissementService;
    private final EtablissementMapper etablissementMapper;

    private final CoreFormationService coreFormationService;
    private final FormationMapper formationMapper;

//    @Override
//    public <T extends EtablissementDataset> EtablissementEntity toEtablissementEntity(@NonNull T dataset, @NonNull String source) {
//        return coreEtablissementService.findEtablissement(dataset.getUai())
//                .map(etablissementEntity -> toEtablissementEntityOld(etablissementEntity, dataset, source))
//                .orElseGet(() -> toEtablissementEntityNew(dataset, source));
//    }

//    private <T extends EtablissementDataset> EtablissementEntity toEtablissementEntityOld(@NonNull EtablissementEntity entity, @NonNull T dataset, @NonNull String source) {
//        // Ne mettre à jour les champs que s'ils sont renseignés
//
//        if (dataset.getCodeNature() != null) {
//            coreEtablissementService.findNature(dataset.getCodeNature()).ifPresent(entity::setNature);
//        }
//
//        if (dataset.getCodeContrat() != null) {
//            coreEtablissementService.findContrat(dataset.getCodeContrat()).ifPresent(entity::setContrat);
//        }
//
//        if (dataset.getDateOuverture() != null)
//            entity.setDateOuverture(dataset.getDateOuverture());
//
//        if (dataset.getDateFermeture() != null)
//            entity.setDateFermeture(dataset.getDateFermeture());
//
//        entity.setUpdatedAt(LocalDateTime.now());
//
//        if (dataset.isActif() != null) {
//            entity.setActif(dataset.isActif());
//        }
//
//        if (dataset.getExternalId() != null) {
//            entity.addIdentifiant(coreEtablissementService.findIdentifiant(entity, dataset.getExternalId().getKey(), dataset.getExternalId().getValue()));
//        }
//
//        entity.addSource(source);
//
//        return entity;
//    }
//
//    private <T extends EtablissementDataset> EtablissementEntity toEtablissementEntityNew(@NonNull T dataset, @NonNull String source) {
//        EtablissementEntity entity = etablissementMapper.toEntity(dataset);
//
//        if (dataset.getCodeCommune() != null && !dataset.getCodeCommune().isBlank()) {
//            var communeOptional = coreReferenceService.findCommune(dataset.getCodeCommune());
//            if (communeOptional.isPresent()) {
//                entity.setCommune(communeOptional.get());
//            } else {
//                communeOptional = coreReferenceService.findCommuneByNom(dataset.getNomCommune());
//                if (communeOptional.isPresent()) {
//                    entity.setCommune(communeOptional.get());
//                } else {
//                    log.warn("Commune inconnue pour {} / {}", dataset.getCodeCommune(), dataset.getNomCommune());
//                }
//            }
//        } else if (dataset.getNomCommune() != null && !dataset.getNomCommune().isBlank()) {
//            var communeOptional = coreReferenceService.findCommuneByNom(dataset.getNomCommune());
//
//            if (communeOptional.isPresent()) {
//                entity.setCommune(communeOptional.get());
//            } else {
//                log.warn("Commune absente pour {} / {}", dataset.getUai(), dataset.getNomCommune());
//            }
//        }
//        if (dataset.getCodeNature() != null) {
//            coreEtablissementService.findNature(dataset.getCodeNature()).ifPresent(entity::setNature);
//        }
//        if (dataset.getCodeContrat() != null) {
//            coreEtablissementService.findContrat(dataset.getCodeContrat()).ifPresent(entity::setContrat);
//        }
//
//        if (dataset.getExternalId() != null) {
//            entity.addIdentifiant(new EtablissementIdentifiantEntity(entity, dataset.getExternalId().getKey(), dataset.getExternalId().getValue()));
//        }
//
//        entity.addSource(source);
//
//        return entity;
//    }

//    @Override
//    public List<EtablissementOptionEntity> toEtablissementOptionEntity(@NonNull EtablissementDataset dataset, @NonNull String source) {
//
//        if (coreEtablissementService.isEtablissementExiste(dataset.getUai())) {
//            return dataset.getOptions()
//                    .stream()
//                    .map(option -> {
//
//                        Optional<EtablissementOptionEntity> o = coreEtablissementService.findOption(dataset.getUai(), option);
//
//                        if (o.isPresent()) {
//                            var o2 = o.get();
//                            o2.addSource(source);
//                            return o2;
//                        } else {
//                            var e = new EtablissementOptionEntity(new EtablissementOptionPK(dataset.getUai(), option), coreEtablissementService.getEtablissementReferenceByUai(dataset.getUai()));
//                            e.addSource(source);
//                            return e;
//                        }
//                    })
//                    .toList();
//        } else {
//            log.warn("Pas d'établissement avec UAI {} pour options", dataset.getUai());
//            return List.of();
//        }
//    }
//
//    @Override
//    public List<EtablissementContactEntity> toEtablissementContactEntity(@NonNull EtablissementDataset dataset, @NonNull String source) {
//        if (coreEtablissementService.isEtablissementExiste(dataset.getUai())) {
//            return dataset.getContacts()
//                    .stream()
//                    .map(c -> {
//                        var e = new EtablissementContactEntity(new EtablissementContactPK(dataset.getUai(), c.getContact(), c.getValeur()), coreEtablissementService.getEtablissementReferenceByUai(dataset.getUai()));
//                        e.addSource(source);
//                        return e;
//                    })
//                    .toList();
//        } else {
//            log.warn("Pas d'établissement avec UAI {} pour contacts", dataset.getUai());
//            return List.of();
//        }
//    }
//
//    @Override
//    public List<EtablissementJPOEntity> toEtablissementJPOEntity(@NonNull EtablissementDataset dataset, @NonNull String source) {
//        if (coreEtablissementService.isEtablissementExiste(dataset.getUai())) {
//            return dataset.getJPO()
//                    .stream()
//                    .filter(Objects::nonNull)
//                    .map(j -> {
//                        var entity = new EtablissementJPOEntity();
//                        entity.setPk(new EtablissementJPOPK(j.getUai(), j.getDateDebut(), j.getDateFin()));
//                        entity.setEtablissement(coreEtablissementService.getEtablissementReferenceByUai(dataset.getUai()));
//                        entity.setHeureDebut(j.getHeureDebut());
//                        entity.setHeureFin(j.getHeureFin());
//                        entity.setCommentaire(j.getCommentaire());
//                        // TODO source
//                        return entity;
//                    })
//                    .toList();
//        } else {
//            log.warn("Pas d'établissement avec UAI {} pour jpo", dataset.getUai());
//            return List.of();
//        }
//    }
//
//    @Override
//    public List<EtablissementSpecialiteEntity> toSpecialiteEntity(@NonNull SpecialitePremiereDataset dataset, @NonNull String source) {
//        if (coreEtablissementService.isEtablissementExiste(dataset.getUai())) {
//            return dataset.getSpecialites().stream().map(
//                    specialiteBac -> new EtablissementSpecialiteEntity(new EtablissementSpecialitePK(dataset.getUai(), specialiteBac), coreEtablissementService.getEtablissementReferenceByUai(dataset.getUai()))
//            ).toList();
//        } else {
//            log.warn("Pas d'établissement avec UAI {} pour specialites", dataset.getUai());
//            return List.of();
//        }
//    }

//    @Override
//    public FormationEntity findFormationByOnisepId(OnisepFormationDataset dataset) {
//        var entity = coreFormationService.findFormationByOnisepId(dataset.getFormationOnisepId());
//
//        if (entity.isPresent()) {
//            return entity.get();
//            // TODO mise à jour
//        } else {
//            Optional<EtablissementEntity> etablissementEntityOpt = coreEtablissementService.findEtablissement(dataset.getEtablissementUai());
//            if (etablissementEntityOpt.isEmpty()) {
//                log.warn("Rien trouvé avec {}/{}/{}/{}", dataset.getEtablissementUai(), dataset.getEtablissmentOnisepId(), dataset.getEtablissementNom(), dataset.getEtablissementAdresse());
//                return null;
//            }
//            FormationEntity formationEntity = formationMapper.toFormationEntity(dataset, etablissementEntityOpt.get());
//            coreFormationService.saveFormation(formationEntity);
//            return formationEntity;
//        }
//    }

//    @Override
//    public OrganismeEntity toOrganismeEntity(@NonNull TravailOrganismeFormationDataset dataset) {
//        return coreEtablissementService.findOrganisme(dataset.getNumeroDeclarationActivite())
//                .map(organismeEntity -> toOrganismeEntityOld(organismeEntity, dataset))
//                .orElseGet(() -> toOrganismeEntityNew(dataset));
//    }

//    @Override
//    @Deprecated
//    public List<EtablissementSportEntity> toEtablissementSportEntity(@NonNull SportDataset dataset, @NonNull Sport.Categorie categorie, @NonNull String source) {
//        if (coreEtablissementService.isEtablissementExiste(dataset.getUai())) {
//            return dataset.getSectionList()
//                    .stream()
//                    .map(String::toUpperCase)
//                    .map(Sport::transformation)
//                    .filter(Objects::nonNull)
//                    .map(section -> {
//                        var e = new EtablissementSportEntity(new EtablissementSportPK(dataset.getUai(), section, categorie), coreEtablissementService.getEtablissementReferenceByUai(dataset.getUai()));
//                        e.addSource(source);
//                        return e;
//                    })
//                    .toList();
//        } else {
//            log.warn("Pas d'établissement avec UAI {} pour {}", dataset.getUai(), categorie);
//            return List.of();
//        }
//    }

//    @Deprecated
//    @Override
//    public List<EtablissementSportEntity> toEtablissementSportEntity(OnisepDispositifDataset dataset, Sport.Categorie categorie, String source) {
//        return List.of();
//    }

//    @Deprecated
//    @Override
//    public List<EtablissementLangueEntity> toLangueEntity(@NonNull OnisepDispositifDataset dataset, @NonNull Langue.Categorie categorie, @NonNull String source) {
//        if (coreEtablissementService.isEtablissementExiste(dataset.getUai())) {
//            return dataset.getLangueList()
//                    .stream()
//                    .map(langue -> {
//                                var e = new EtablissementLangueEntity(new EtablissementLanguePK(dataset.getUai(), langue, categorie, ""), coreEtablissementService.getEtablissementReferenceByUai(dataset.getUai()));
//                                e.addSource(source);
//                                return e;
//                            }
//                    )
//                    .toList();
//        } else {
//            log.warn("Pas d'établissement avec UAI {} pour langues {}", dataset.getUai(), categorie);
//            return List.of();
//        }
//    }

//    @Override
//    public ActionFormationEntity toActionFormationEntity(@NonNull ParcoursupFormationDataset dataset, @NonNull FormationEntity formationEntity) {
//
//        Optional<ActionFormationEntity> actionFormationEntityOptional = coreFormationService.findActionFormationByParcoursupId(dataset.getCodeInterneFormation());
//
//        if (actionFormationEntityOptional.isPresent()) {
//            return actionFormationEntityOptional.get();
//        } else {
//            ActionFormationEntity entity = new ActionFormationEntity();
//            entity.setId(UUID.nameUUIDFromBytes(dataset.getCodeInterneFormation().toString().getBytes()));
//
//            entity.setFormation(formationEntity);
//            entity.setUrlAction(dataset.getLienFormation());
//            entity.setSession(dataset.getAnnee());
//            entity.setConditionsSpecifiques(dataset.getComplements());
//
//            Optional<EtablissementEntity> etablissementEntityOptional = coreEtablissementService.findEtablissement(dataset.getUai());
//
//            if (etablissementEntityOptional.isPresent()) {
//                entity.setEtablissement(etablissementEntityOptional.get());
//            } else {
//                log.warn("Pas d'établissement pour {}", dataset.getUai());
//            }
//
//            entity.setModalitesRecrutement("parcoursup");
//            entity.setHebergement(dataset.getInternat());
//
//            entity.setParcoursupId(dataset.getCodeInterneFormation());
//
//            return entity;
//        }
//    }

//    @Override
//    public <T extends Effectifs & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(@NonNull T dataset) {
//        var uai = dataset.getUai();
//        var annee = dataset.getAnnee();
//
//        log.info("1");
//        if (coreEtablissementService.isEtablissementExiste(uai)) {
//            log.info("2");
//            Optional<EtablissementMetadataEntity> metadataEntityOptional = coreEtablissementService.findMetadata(uai, annee);
//
//            if (metadataEntityOptional.isPresent()) {
//                var entity = metadataEntityOptional.get();
//                var metadatas = entity.getMetadatas();
//                if (dataset.getEffectifs() != null) {
//                    metadatas.setEffectifs(dataset.getEffectifs());
//                }
//                entity.setMetadatas(metadatas);
//                return entity;
//            } else {
//                log.info("3");
//                var entity = new EtablissementMetadataEntity(new EtablissementAnneePK(annee, uai), coreEtablissementService.getEtablissementReferenceByUai(uai));
//                var metadatas = entity.getMetadatas();
//                if (dataset.getEffectifs() != null) {
//                    metadatas.setEffectifs(dataset.getEffectifs());
//                }
//                entity.setMetadatas(metadatas);
//                return entity;
//            }
//        } else {
//            log.warn("Pas d'établissement avec UAI {} pour effectifs", uai);
//            return null;
//        }
//    }

//    @Override
//    public <T extends IndicateurValeurAjoutee & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(@NonNull T dataset) {
//        var uai = dataset.getUai();
//        var annee = dataset.getAnnee();
//
//        if (coreEtablissementService.isEtablissementExiste(uai)) {
//            Optional<EtablissementMetadataEntity> metadataEntityOptional = coreEtablissementService.findMetadata(uai, annee);
//
//            if (metadataEntityOptional.isPresent()) {
//                var entity = metadataEntityOptional.get();
//                var metadatas = entity.getMetadatas();
//                metadatas.setIva(etablissementMapper.toIndicateurValeurAjouteeDto(dataset));
//                entity.setMetadatas(metadatas);
//                return entity;
//            } else {
//                var entity = new EtablissementMetadataEntity(new EtablissementAnneePK(annee, uai), coreEtablissementService.getEtablissementReferenceByUai(uai));
//                var metadatas = entity.getMetadatas();
//                metadatas.setIva(etablissementMapper.toIndicateurValeurAjouteeDto(dataset));
//                entity.setMetadatas(metadatas);
//                return entity;
//            }
//        } else {
//            log.warn("Pas d'établissement avec UAI {} pour IVA", uai);
//            return null;
//        }
//    }

//    @Override
//    public <T extends IndicePositionSociale & Metadata> EtablissementMetadataEntity toEtablissementMetadataEntity(@NonNull T dataset) {
//        var uai = dataset.getUai();
//        var annee = dataset.getAnnee();
//
//        if (coreEtablissementService.isEtablissementExiste(uai)) {
//            Optional<EtablissementMetadataEntity> metadataEntityOptional = coreEtablissementService.findMetadata(uai, annee);
//
//            if (metadataEntityOptional.isPresent()) {
//                var entity = metadataEntityOptional.get();
//                var metadatas = entity.getMetadatas();
//                metadatas.setIps(etablissementMapper.toIndicePositionSocialeDto(dataset));
//                entity.setMetadatas(metadatas);
//                return entity;
//            } else {
//                var entity = new EtablissementMetadataEntity(new EtablissementAnneePK(annee, uai), coreEtablissementService.getEtablissementReferenceByUai(uai));
//                var metadatas = entity.getMetadatas();
//                metadatas.setIps(etablissementMapper.toIndicePositionSocialeDto(dataset));
//                entity.setMetadatas(metadatas);
//                return entity;
//            }
//        } else {
//            log.warn("Pas d'établissement avec UAI {} pour IPS", uai);
//            return null;
//        }
//    }

//    private OrganismeEntity toOrganismeEntityNew(TravailOrganismeFormationDataset dataset) {
//        var o = etablissementMapper.toOrganismeEntity(dataset);
//        coreReferenceService.findCommuneByNom(dataset.getNomCommune()).ifPresent(o::setCommune);
//        return o;
//    }
//
//    private OrganismeEntity toOrganismeEntityOld(OrganismeEntity organismeEntity, TravailOrganismeFormationDataset dataset) {
//        organismeEntity.setActionsDeFormation(dataset.getActionsDeFormation());
//        organismeEntity.setBilansDeCompetences(dataset.getBilansDeCompetences());
//        organismeEntity.setValidationAcquisExperience(dataset.getValidationAcquisExperience());
//        organismeEntity.setActionsDeFormationParApprentissage(dataset.getActionsDeFormationParApprentissage());
//        organismeEntity.setDateDebut(toLocalDate(dataset.getDateDebut()));
//        organismeEntity.setDateFin(toLocalDate(dataset.getDateFin()));
//        organismeEntity.setDateDerniereDeclaration(toLocalDate(dataset.getDateDerniereDeclaration()));
//
//        return organismeEntity;
//    }
}
