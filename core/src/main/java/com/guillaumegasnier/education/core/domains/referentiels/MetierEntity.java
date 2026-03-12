package com.guillaumegasnier.education.core.domains.referentiels;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import com.guillaumegasnier.education.core.dto.MetierMetadataDto;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

/**
 * Répertoire Opérationnel des Métiers et des Emplois
 */
@Getter
@Setter
@Entity
@Table(name = "metiers")
public class MetierEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "BPCHAR(5)", length = 5, nullable = false)
    private String code;

    private int codeOgr;

    private String nom;

    @Type(JsonType.class)
    @Column(name = "metadatas", columnDefinition = "jsonb")
    private MetierMetadataDto metdatas = new MetierMetadataDto();

    @ManyToOne(fetch = FetchType.LAZY)
    private MetierEntity metierParent;

}
