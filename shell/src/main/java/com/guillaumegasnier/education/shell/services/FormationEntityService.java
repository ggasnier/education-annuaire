package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.domains.formations.ActionFormationEntity;
import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.shell.datasets.formations.OnisepFormationDataset;
import com.guillaumegasnier.education.shell.datasets.formations.ParcoursupFormationDataset;
import org.springframework.lang.NonNull;

@Deprecated
public interface FormationEntityService {

    FormationEntity findFormationByOnisepId(OnisepFormationDataset dataset);

    ActionFormationEntity toActionFormationEntity(@NonNull ParcoursupFormationDataset parcoursupFormationDataset, @NonNull FormationEntity entity);

}
