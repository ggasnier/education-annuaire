package com.guillaumegasnier.education.core.repositories.referentiels;

import com.guillaumegasnier.education.core.domains.referentiels.MetierSavoirEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetierSavoirRepository extends CrudRepository<MetierSavoirEntity, Integer> {
}
