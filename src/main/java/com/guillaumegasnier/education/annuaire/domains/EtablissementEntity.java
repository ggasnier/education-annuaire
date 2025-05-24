package com.guillaumegasnier.education.annuaire.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "etablissements")
public class EtablissementEntity {

    @Id
    @Column(columnDefinition = "CHAR(8)", unique = true, updatable = false, nullable = false, length = 8)
    private String uai;
}
