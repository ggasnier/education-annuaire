package com.guillaumegasnier.education.shell.transformers;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import com.guillaumegasnier.education.shell.datasets.etablissements.TravailOrganismeFormationDataset;
import com.guillaumegasnier.education.shell.dto.formations.ActionFormationDTO;
import com.guillaumegasnier.education.shell.dto.formations.FormationDTO;
import org.springframework.lang.NonNull;

public interface FormationTransformer {


    OrganismeEntity toOrganismeEntity(@NonNull TravailOrganismeFormationDataset dataset);

    default FormationEntity toFormationEntity(@NonNull FormationDTO dto, String source) {
        return null;
    }

    ActionFormationEntity toActionFormationEntity(@NonNull ActionFormationDTO dto, @NonNull String source);

    default FormationDTO recalculId(@NonNull FormationDTO dto) {
        return dto;
    }
}
