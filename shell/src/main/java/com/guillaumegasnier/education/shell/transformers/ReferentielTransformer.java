package com.guillaumegasnier.education.shell.transformers;

import com.guillaumegasnier.education.core.domains.referentiels.CertificationNationaleEntity;
import com.guillaumegasnier.education.core.domains.referentiels.CompetenceEntity;
import com.guillaumegasnier.education.core.domains.referentiels.MetierEntity;
import com.guillaumegasnier.education.shell.datasets.referentiels.RomeDataset;
import com.guillaumegasnier.education.shell.dto.referentiels.CertificationDTO;
import com.guillaumegasnier.education.shell.dto.referentiels.CompetenceDTO;
import org.springframework.lang.NonNull;

public interface ReferentielTransformer {

    MetierEntity toMetierEntity(@NonNull RomeDataset dataset);

    CompetenceEntity toCompetenceEntity(CompetenceDTO competenceDTO);

    CertificationNationaleEntity toCertificationNationaleEntity(CertificationDTO dto);
}
