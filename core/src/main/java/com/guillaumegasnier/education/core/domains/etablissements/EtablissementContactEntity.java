package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractSourcesEntity;
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
}
