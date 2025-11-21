package com.guillaumegasnier.education.core.repositories;

import com.guillaumegasnier.education.core.domains.organismes.OrganismeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganismeRepository extends JpaRepository<OrganismeEntity, String> {
}
