package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.domains.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.etablissements.*;
import com.guillaumegasnier.education.shell.datasets.ips.IPSDataset;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShellEntityService {

    <T extends EtablissementDataset> EtablissementEntity toEtablissementEntity(@NonNull T dataset, String source);

    List<OptionEtablissementEntity> toOptionEtablissementEntity(@NonNull EtablissementDataset dataset);

    OptionEtablissementEntity toOptionEtablissementEntity(@NonNull SectionBinationaleDataset dataset);

    List<SectionSportiveEntity> toSectionSportiveEntity(@NonNull SectionSportiveDataset dataset);

    List<SpecialiteEntity> toSpecialiteEntity(@NonNull SpecialitePremiereDataset dataset);

    List<SectionInternationaleEntity> toSectionInternationaleEntity(@NonNull SectionInternationaleDataset dataset);

    IndicePositionSocialeEntity toIndicePositionSocialeEntity(@NonNull IPSDataset dataset, @NonNull String categorie);

    LangueEntity toLangueEntity(@NonNull LangueDataset dataset);

    SportEtudeEntity toSportEtudeEntity(@NonNull SectionSportEtudeDataset dataset);
}
