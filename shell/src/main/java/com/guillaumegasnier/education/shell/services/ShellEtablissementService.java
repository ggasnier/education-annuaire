package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.enums.Sport;
import com.guillaumegasnier.education.core.validations.Effectifs;
import com.guillaumegasnier.education.core.validations.IndicateurValeurAjoutee;
import com.guillaumegasnier.education.core.validations.IndicePositionSociale;
import com.guillaumegasnier.education.core.validations.Metadata;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Chaque méthode prend en entrée un dataset et la source du dataset (format libre)
 */
public interface ShellEtablissementService {

    void createOrUpdateEtablissements(@NonNull List<? extends EtablissementDataset> datasets, @NonNull String source);

    void createOrUpdateLangues(@NonNull List<LangueDataset> datasets, @NonNull String source);

    void createOrUpdateSpecialites(@NonNull List<SpecialitePremiereDataset> datasets, @NonNull String source);

    void createOrUpdateSectionsInternationales(@NonNull List<SectionInternationaleDataset> datasets, @NonNull String source);

    void createOrUpdateSectionsBinationales(@NonNull List<SectionBinationaleDataset> datasets, @NonNull String source);

    void createOrUpdateSports(@NonNull List<SportDataset> datasets, Sport.Categorie categorie, @NonNull String source);

    void createOrUpdateDispositifs(@NonNull List<OnisepDispositifDataset> datasets, @NonNull String source);

    void createOrUpdateEuroscol(@NonNull List<EuroscolDataset> datasets, @NonNull String source);

    <T extends IndicePositionSociale & Metadata> void createOrUpdateIPS(@NonNull List<T> datasets);

    <T extends Effectifs & Metadata> void createOrUpdateEffectifs(@NonNull List<T> datasets);

    <T extends IndicateurValeurAjoutee & Metadata> void createOrUpdateIVA(@NonNull List<T> datasets);

}
