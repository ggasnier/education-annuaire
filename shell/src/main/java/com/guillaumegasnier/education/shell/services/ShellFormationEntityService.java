package com.guillaumegasnier.education.shell.services;

import com.guillaumegasnier.education.core.domains.formations.FormationEntity;
import com.guillaumegasnier.education.shell.datasets.FormationType;

@Deprecated
public interface ShellFormationEntityService {

    FormationEntity toFormationEntity(FormationType dataset);
}
