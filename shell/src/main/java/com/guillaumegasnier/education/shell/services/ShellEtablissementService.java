package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.validations.Effectifs;
import com.guillaumegasnier.education.core.validations.IndicateurValeurAjoutee;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShellEtablissementService {

    void createOrUpdateEtablissements(@NonNull List<? extends EtablissementDataset> datasets, String source);

    void createOrUpdateNatures(@NonNull List<NatureDataset> datasets);

    void createOrUpdateContrats(@NonNull List<ContratDataset> datasets);

    void createOrUpdateLangues(@NonNull List<LangueDataset> datasets);

    void createOrUpdateSpecialites(@NonNull List<SpecialitePremiereDataset> datasets);

    void createOrUpdateSectionsInternationales(@NonNull List<SectionInternationaleDataset> datasets);

    void createOrUpdateSectionsBinationales(@NonNull List<SectionBinationaleDataset> datasets);

    void createOrUpdateSports(@NonNull List<SportDataset> datasets, Sport.Categorie categorie);

    void createOrUpdateDispositifs(@NonNull List<OnisepDispositifDataset> datasets);

    <T extends IndicePositionSociale & Metadata> void createOrUpdateIPS(@NonNull List<T> datasets);

    <T extends Effectifs & Metadata> void createOrUpdateEffectifs(@NonNull List<T> datasets);

    <T extends IndicateurValeurAjoutee & Metadata> void createOrUpdateIVA(@NonNull List<T> datasets);

    void createOrUpdateEuroscol(@NonNull List<EuroscolDataset> datasets);
}
