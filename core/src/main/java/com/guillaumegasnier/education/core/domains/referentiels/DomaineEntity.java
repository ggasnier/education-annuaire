package com.guillaumegasnier.education.core.domains.referentiels;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rome_domaines")
public class DomaineEntity extends AbstractEntity {

    /**
     * 1, 2, 3
     */
    @Id
    private int code;

    private String nom;
}
