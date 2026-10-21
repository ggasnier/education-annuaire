package com.guillaumegasnier.education.shell.transformers;

import com.guillaumegasnier.education.core.domains.referentiels.*;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeSavoirDataset;
import com.guillaumegasnier.education.shell.datasets.referentiels.SavoirDataset;
import com.guillaumegasnier.education.shell.dto.referentiels.*;
import org.jspecify.annotations.NonNull;

public interface ReferentielTransformer {

    MetierEntity toMetierEntity(@NonNull RomeDataset dataset);

    CompetenceEntity toCompetenceEntity(@NonNull CompetenceDTO competenceDTO);

    CertificationNationaleEntity toCertificationNationaleEntity(CertificationDTO dto);

    MetierSavoirEntity toMetierSavoirEntity(@NonNull SavoirDataset savoirDataset);

    MetierSavoirLienEntity toMetierSavoirLienEntity(RomeSavoirDataset romeSavoirDataset);

    ObjectifEntity toObjectifEntity(ObjectifDTO dto);

    EnjeuEntity toEnjeuEntity(EnjeuDTO dto);

    DomaineEntity toDomaineCompetenceEntity(DomaineCompetenceDTO dto);

    MacroCompetenceEntity toMacroCompetenceEntity(MacroCompetenceDTO dto);

    MetierCompetenceEntity toMetierCompetenceEntity(RomeSavoirDataset dataset);
}
