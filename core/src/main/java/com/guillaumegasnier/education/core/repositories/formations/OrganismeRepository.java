package com.guillaumegasnier.education.core.repositories.formations;

import com.guillaumegasnier.education.core.domains.formations.OrganismeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganismeRepository extends JpaRepository<OrganismeEntity, String> {
}
