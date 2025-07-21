package com.guillaumegasnier.education.shell.services;


import com.guillaumegasnier.education.core.etablissements.services.EtablissementService;
import com.guillaumegasnier.education.core.references.services.ReferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * A dispatcher dans les diféfrents services
 */
@Deprecated
@Slf4j
@Service
@RequiredArgsConstructor
public class ImportService {

    private final EtablissementService etablissementService;
    private final ReferenceService referenceService;
    private final FileService fileService;

//    @Value("${spring.profiles.active}")
//    private String env;
//
//    public ImportService(EtablissementService etablissementService, ReferenceService referenceService, FileService fileService) {
//        this.etablissementService = etablissementService;
//        this.referenceService = referenceService;
//        this.fileService = fileService;
//    }
//
//    public String importAcademies() {
//        String location = env.equals("local") ? ACADEMIES.getPath() : ACADEMIES.getUrl();
//        return referenceService.createOrUpdateAcademie(fileService.importCSV(location, AcademieDataset.class, ',', Charset.forName("windows-1252")));
//
//    }
//
//    public String importRegions() {
//        String location = env.equals("local") ? REGIONS.getPath() : REGIONS.getUrl();
//        return referenceService.createOrUpdateRegion(fileService.importCSV(location, RegionDataset.class, ',', StandardCharsets.UTF_8));
//    }
//
//    public String importDepartements() {
//        String location = env.equals("local") ? DEPARTEMENTS.getPath() : DEPARTEMENTS.getUrl();
//        String location2 = env.equals("local") ? DEPARTEMENTS_COMER.getPath() : DEPARTEMENTS_COMER.getUrl();
//        String location3 = env.equals("local") ? ACADEMIES_DEPARTEMENTS.getPath() : ACADEMIES_DEPARTEMENTS.getUrl();
//
//        HashMap<String, String> lienDepartementAcademie = referenceService.setAcademieDepartement(fileService.importCSV(location3, AcademieDepartementDataset.class, ',', Charset.forName("windows-1252")));
//
//        return referenceService.createOrUpdateDepartement(fileService.importCSV(location, DepartementDataset.class, ',', StandardCharsets.UTF_8), lienDepartementAcademie) +
//                referenceService.createOrUpdateDepartement(fileService.importCSV(location2, DepartementOutreMerDataset.class, ',', StandardCharsets.UTF_8), lienDepartementAcademie);
//    }
//
//    public String importCommunes() {
//        String location = env.equals("local") ? COMMUNES.getPath() : COMMUNES.getUrl();
//        String location2 = env.equals("local") ? COMMUNES_COMER.getPath() : COMMUNES_COMER.getUrl();
//        return referenceService.createOrUpdateCommune(fileService.importCSV(location, CommuneDataset.class, ',', StandardCharsets.UTF_8)) +
//                referenceService.createOrUpdateCommune(fileService.importCSV(location2, CommuneOutreMerDataset.class, ',', StandardCharsets.UTF_8));
//    }
//
//    public String importNatures() {
//        String location = env.equals("local") ? NATURES.getPath() : NATURES.getUrl();
//        return referenceService.createOrUpdateNature(fileService.importCSV(location, NatureDataset.class, ',', Charset.forName("windows-1252")));
//    }
//
//    public String importContrats() {
//        String location = env.equals("local") ? CONTRATS.getPath() : CONTRATS.getUrl();
//        return referenceService.createOrUpdateContrat(fileService.importCSV(location, ContratDataset.class, ',', Charset.forName("windows-1252")));
//    }
//
//    public String importEnEtablissements() {
//        String location = env.equals("local") ? EN_ETABS_OUVERTS.getPath() : EN_ETABS_OUVERTS.getUrl();
//        return etablissementService.createOrUpdateEtablissement(fileService.importCSV(location, EnEtablissementDataset.class, ';', StandardCharsets.UTF_8), "en");
//    }
//
//    public String importEsrEtablissements() {
//        String location = env.equals("local") ? ESR_ETABS_OUVERTS.getPath() : ESR_ETABS_OUVERTS.getUrl();
//        return etablissementService.createOrUpdateEtablissement(fileService.importCSV(location, EsrEtablissementDataset.class, ';', StandardCharsets.UTF_8), "esr");
//    }
//
//    public String importCarifEtablissements() {
//        String location = env.equals("local") ? CARIF_ETABS_OUVERTS.getPath() : CARIF_ETABS_OUVERTS.getUrl();
//        return etablissementService.createOrUpdateEtablissement(fileService.importCSV(location, CarifEtablissementDataset.class, ';', StandardCharsets.UTF_8), "carif");
//    }
//
//    public String importIPSColleges() {
//        String location = env.equals("local") ? IPS_COLLEGES_1.getPath() : IPS_COLLEGES_1.getUrl();
//        return etablissementService.createOrUpdateIPS(fileService.importCSV(location, IPSCollegeDataset.class, ';', StandardCharsets.UTF_8));
//
//    }
}
