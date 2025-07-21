package com.guillaumegasnier.education.core.etablissements.services;


import com.guillaumegasnier.education.core.etablissements.entities.NatureEntity;
import com.guillaumegasnier.education.core.etablissements.mappers.EtablissementMapper;
import com.guillaumegasnier.education.core.etablissements.repositories.ContratRepository;
import com.guillaumegasnier.education.core.etablissements.repositories.EtablissementRepository;
import com.guillaumegasnier.education.core.etablissements.repositories.IndicePositionSocialeRepository;
import com.guillaumegasnier.education.core.etablissements.repositories.NatureRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class EtablissementService {

    private final EtablissementRepository etablissementRepository;
    private final NatureRepository natureRepository;
    private final EtablissementMapper etablissementMapper;
    private final IndicePositionSocialeRepository indicePositionSocialeRepository;
    private final Validator validator;
    private final ContratRepository contratRepository;

//    /**
//     * <p>Création d'un établissement</p>
//     *
//     * @param dto Données d'entrées, à valider
//     * @return Le Dto de l'établissement créé
//     */
//    public Optional<EtablissementDto> createEtablissement(@NonNull EtablissementRequestDto dto) {
//        EtablissementEntity entity = etablissementMapper.toEntity(dto);
//        entity.setNature(this.getNature(dto.getCodeNature()));
//        entity.setCommune(this.getCommune(dto.getCodeCommune()));
//        etablissementRepository.save(entity);
//        return Optional.of(etablissementMapper.toDto(entity));
//    }

//    private CommuneEntity getCommune(String codeCommune) {
//        if (codeCommune == null) return null;
//        return communeRepository.getReferenceById(codeCommune);
//    }

    private NatureEntity getNature(String codeNature) {
        if (codeNature == null) return null;
        return natureRepository.getReferenceById(codeNature);
    }


//    public Optional<EtablissementDto> updateEtablissement(@NonNull EtablissementRequestDto dto) {
//        return Optional.empty();
//    }

//    public List<IPSDto> getEtablissementIPS(@NonNull String uai) {
//        return etablissementMapper.toDto(indicePositionSocialeRepository.findAllByPkUaiOrderByPkAnneeDesc(uai));
//    }
//
//    public Optional<IPSDto> createOrUpdateIndice(@NonNull String uai, @NonNull IPSRequestDto dto) {
//        var entity = indicePositionSocialeRepository.findByPkUaiAndPkAnnee(uai, dto.getAnnee())
//                .orElseGet(() -> new IndicePositionSocialeEntity(uai, dto.getAnnee()));
//
//        entity.setIndice(dto.getIndice());
//        entity.setEcartType(dto.getEcartType());
//        entity.setEtablissement(etablissementRepository.getReferenceById(uai));
//
//        return Optional.of(etablissementMapper.toDto(indicePositionSocialeRepository.save(entity)));
//    }

//    public Page<EtablissementDto> searchEtablissement(int page) {
//        Pageable pageable = PageRequest.of(page, 20);
//
//        return etablissementRepository.findAll(pageable).map(etablissementMapper::toDto);
//    }

//    /**
//     * @param datasets
//     * @return
//     */
//    @Transactional
//    public String createOrUpdateEtablissement(@NonNull List<? extends EtablissementDataset> datasets, String source) {
//        AtomicInteger counter = new AtomicInteger(0);
//        int size = datasets.size();
//        int progressStep = Math.max(size / 10, 1);
//
//        List<EtablissementEntity> entities = datasets
//                .stream()
//                .flatMap(dataset -> Arrays.stream(dataset.getUai().split(";"))
//                        .map(String::trim)
//                        .filter(uai -> !uai.isEmpty())
//                        .map(uai -> {
//                            EtablissementDataset copy = new EtablissementDataset();
//                            BeanUtils.copyProperties(dataset, copy);
//                            copy.setUai(uai);
//                            return copy;
//                        }))
//                .flatMap(dataset -> {
//                    String siretField = dataset.getSiret();
//                    if (siretField == null || siretField.isBlank()) {
//                        return Stream.of(dataset);
//                    }
//
//                    return Arrays.stream(siretField.split(","))
//                            .map(String::trim)
//                            .filter(siret -> !siret.isEmpty())
//                            .distinct()
//                            .map(siret -> {
//                                EtablissementDataset copy = new EtablissementDataset();
//                                BeanUtils.copyProperties(dataset, copy);
//                                copy.setSiret(siret);
//                                return copy;
//                            });
//                })
//                .map(dataset -> {
//                    int count = counter.incrementAndGet();
//                    if (count % progressStep == 0) {
//                        var percent = (count * 100) / size;
//                        log.info("Avancement {}%", percent);
//                    }
//                    return toEtablissementEntity(dataset, source);
//                })
//                .filter(entity -> {
//                    Set<ConstraintViolation<EtablissementEntity>> violations = validator.validate(entity);
//                    if (!violations.isEmpty()) {
//                        // TODO : à enregister dans une autre table en JSONB
//                        violations.forEach(v -> {
//                                    if (v.getMessage().equals("SIRET invalide")) {
//                                        entity.setSiret(null);
//                                        if (violations.size() == 1) // Si c'est la seule erreur
//                                            violations.clear();
//                                    } else {
//                                        log.warn("Validation failed on {}.{}: {} ({})", entity.getClass().getSimpleName(), v.getPropertyPath(), v.getMessage(), v.getInvalidValue());
//                                    }
//                                }
//                        );
//                    }
//                    return violations.isEmpty();
//                })
//                .toList();
//
//        saveInChunks(new ArrayList<>(entities), 1000);
//        return "OK";
//    }
//
//    @Transactional
//    protected <T extends EtablissementDataset> EtablissementEntity toEtablissementEntity(@NonNull T dataset, String source) {
//
//        EtablissementEntity entity = etablissementRepository.findById(dataset.getUai()).orElseGet(() -> etablissementMapper.toEntity(dataset));
//
//        entity.setSiret(dataset.getSiret());
//        entity.setAdresse(dataset.getAdresse());
//        entity.setComplement(dataset.getComplement());
//
//        // contacts
//        entity.setContacts(this.mergeContacts(entity, dataset.getContacts()));
//
//        // siret (seulement si une valeur est renseignée)
//        if (dataset.getSiret() != null)
//            entity.setSiret(dataset.getSiret());
//
//        // etat (seulement si une valeur est renseignée)
//        if (dataset.getEtat() != null)
//            entity.setEtat(dataset.getEtat());
//
////        communeRepository.findById(dataset.getCodeCommune()).ifPresent(commune ->
////                setIfChanged(entity::getCommune, entity::setCommune, commune)
////        );
////        natureRepository.findById(dataset.getCodeNature()).ifPresent(nature ->
////                setIfChanged(entity::getNature, entity::setNature, nature)
////        );
////        contratRepository.findById(dataset.getCodeContrat()).ifPresent(contrat ->
////                setIfChanged(entity::getContrat, entity::setContrat, contrat)
////        );
//
//        if (dataset.getCodeCommune() != null) {
//            communeRepository.findById(dataset.getCodeCommune()).ifPresent(entity::setCommune);
//        }
//        if (dataset.getCodeNature() != null) {
//            natureRepository.findById(dataset.getCodeNature()).ifPresent(entity::setNature);
//        }
//        if (dataset.getCodeContrat() != null) {
//            contratRepository.findById(dataset.getCodeContrat()).ifPresent(entity::setContrat);
//        }
//
//        // Sources
//        entity.addSource(source);
//
//        return entity;
//    }
//
//    @Transactional
//    protected List<ContactEntity> mergeContacts(EtablissementEntity entity, List<ContactEtablissementDataset> contacts) {
//        if (entity.getContacts() == null) {
//            entity.setContacts(new ArrayList<>());
//        }
//
//        Set<ContactPk> existingPks = entity.getContacts()
//                .stream()
//                .map(ContactEntity::getPk)
//                .collect(Collectors.toSet());
//
//        List<ContactEntity> nouveaux = toContactEntityList(entity, contacts).stream()
//                .filter(c -> !existingPks.contains(c.getPk()))
//                .toList();
//
//        entity.getContacts().addAll(nouveaux);
//
//        return entity.getContacts();
//    }
//
//    @Transactional
//    protected List<ContactEntity> toContactEntityList(@NonNull EtablissementEntity entity, @NonNull List<ContactEtablissementDataset> contacts) {
//
//        List<ContactEntity> contactEntityList = new ArrayList<>();
//
//        for (ContactEtablissementDataset contact : contacts) {
//            ContactEntity contactEntity = new ContactEntity();
//            contactEntity.setPk(new ContactPk(entity.getUai(), contact.getClef(), contact.getValeur()));
//            contactEntity.setEtablissement(entity);
//            contactEntityList.add(contactEntity);
//        }
//
//        return contactEntityList;
//    }
//
//    public void saveInChunks(List<EtablissementEntity> entities, int chunkSize) {
//        try {
//            for (int i = 0; i < entities.size(); i += chunkSize) {
//                int end = Math.min(i + chunkSize, entities.size());
//                List<EtablissementEntity> sublist = entities.subList(i, end);
//                etablissementRepository.saveAll(sublist);
//                etablissementRepository.flush();
//            }
//        } catch (Exception e) {
//            log.error("Erreur JPA : {}", e.getMessage(), e);
//            throw e;
//        }
//    }
//
//    public String createOrUpdateIPS(List<? extends IPSDataset> datasets) {
//
//        for (IPSDataset dataset : datasets) {
//            log.info(dataset.toString());
//        }
//
//        return "TODO";
//    }
}
