package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.dto.EtablissementMetadataDto;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

/**
 * Metadatas pour chaque établissement, par année (qui correspond à la rentrée scolaire)
 *
 */
@Getter
@Setter
@Entity
@Table(name = "etablissements_metadatas")
@ToString
@NoArgsConstructor
public class EtablissementMetadataEntity extends AbstractEntity {

    @EmbeddedId
    private EtablissementAnneePK pk;

    @MapsId("uai")
    @ManyToOne
    @JoinColumn(name = "uai", columnDefinition = "BPCHAR(8)", foreignKey = @ForeignKey(name = "fk_metadatas_etablissements"), nullable = false)
    private EtablissementEntity etablissement;

    @Type(JsonType.class)
    @Column(name = "metadatas", columnDefinition = "jsonb")
    private EtablissementMetadataDto metadatas = new EtablissementMetadataDto();

    public EtablissementMetadataEntity(EtablissementAnneePK pk, EtablissementEntity etablissement) {
        this.pk = pk;
        this.etablissement = etablissement;
    }
}
