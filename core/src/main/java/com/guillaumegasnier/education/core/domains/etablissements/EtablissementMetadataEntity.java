package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.dto.EtablissementMetadataDto;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

/**
 * Metadatas pour chaque établissement, par année (qui correspond à la rentrée scolaire)
 *
 */
@Getter
@Setter
@Entity
@Table(name = "etablissements_metadatas")
public class EtablissementMetadataEntity extends AbstractEntity {

    @EmbeddedId
    private EtablissementMetadataPK pk;

    @ManyToOne
    @JoinColumn(name = "etablissement_id", foreignKey = @ForeignKey(name = "fk_metadatas_etablissements"), columnDefinition = "UUID", nullable = false)
    private EtablissementEntity etablissement;

    @Type(JsonType.class)
    @Column(name = "metadatas", columnDefinition = "jsonb")
    private EtablissementMetadataDto metadatas;

}
