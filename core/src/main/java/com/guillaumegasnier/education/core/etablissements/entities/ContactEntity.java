package com.guillaumegasnier.education.core.etablissements.entities;

import com.guillaumegasnier.education.core.commun.entities.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contacts")
public class ContactEntity extends AbstractEntity {

    @EmbeddedId
    private ContactPk pk;

    @MapsId("uai")
    @ManyToOne
    @JoinColumn(name = "uai", referencedColumnName = "uai", columnDefinition = "CHAR(8)", nullable = false, foreignKey = @ForeignKey(name = "fk_contacts_etablissements"))
    private EtablissementEntity etablissement;
}


