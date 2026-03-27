package com.guillaumegasnier.education.core.repositories.territoires;

import com.guillaumegasnier.education.core.domains.territoires.AcademieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademieRepository extends JpaRepository<AcademieEntity, String> {

    List<AcademieEntity> findAllByOrderByCode();

}
