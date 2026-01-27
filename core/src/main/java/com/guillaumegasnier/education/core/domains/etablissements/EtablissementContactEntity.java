package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractSourcesEntity;
import com.guillaumegasnier.education.core.enums.Contact;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "etablissements_contacts")
public class EtablissementContactEntity extends AbstractSourcesEntity {

    @EmbeddedId
    private EtablissementContactPK pk;

    @MapsId("uai")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uai", columnDefinition = "BPCHAR(8)", foreignKey = @ForeignKey(name = "fk_contacts_etablissements"), nullable = false)
    private EtablissementEntity etablissement;

    public EtablissementContactEntity(String uai, Contact contact, String valeur, EtablissementEntity etablissement) {
        pk = new EtablissementContactPK(uai, contact, valeur);
        this.etablissement = etablissement;
    }
}
