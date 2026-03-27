package com.guillaumegasnier.education.core.domains.territoires;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "communes")
public class CommuneEntity extends AbstractEntity {

    /**
     * FR : code_insee
     * Les autres pays : geoname_id
     */
    @Id
    @Column(columnDefinition = "VARCHAR(10)", length = 10, unique = true)
    private String code;

    @NotBlank
    private String nom;

    @ManyToOne
    @JoinColumn(name = "code_pays", foreignKey = @ForeignKey(name = "fk_communes_pays"))
    private PaysEntity pays;

    @ManyToOne
    @JoinColumn(name = "code_departement", foreignKey = @ForeignKey(name = "fk_communes_departements"))
    private DepartementEntity departement;
}
