package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.validations.Effectifs;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShellEtablissementService {

    String createOrUpdateEtablissements(@NonNull List<? extends EtablissementDataset> datasets, String source);

    String createOrUpdateNatures(@NonNull List<NatureDataset> datasets);

    String createOrUpdateContrats(@NonNull List<ContratDataset> datasets);

//    String createOrUpdateIPS(@NonNull List<? extends IPSDataset> datasets, String categorie);

//    String createOrUpdateSectionsSportives(@NonNull List<SectionSportiveDataset> datasets);

//    String createOrUpdateSectionsSportEtudes(@NonNull List<SectionSportEtudeDataset> datasets);

    String createOrUpdateLangues(@NonNull List<LangueDataset> datasets);

    String createOrUpdateSpecialites(@NonNull List<SpecialitePremiereDataset> datasets);

    //String createOrUpdateSectionsInternationales(@NonNull List<SectionInternationaleDataset> datasets);

    String createOrUpdateSectionsBinationales(@NonNull List<SectionBinationaleDataset> datasets);

    String createOrUpdateEtablissementsRecherche();

    String createOrUpdateOrganismes(@NonNull List<TravailOrganismeFormationDataset> datasets);

    String createOrUpdateSports(@NonNull List<SportDataset> datasets, String categorie);

    String createOrUpdateDispositifs(@NonNull List<OnisepDispositifDataset> datasets);

    <T extends IndicePositionSociale & Effectifs & Metadata> void createOrUpdateIPS(List<T> datasets);
}
