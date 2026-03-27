package com.guillaumegasnier.education.core.domains.etablissements;

import com.guillaumegasnier.education.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "etablissements_identifiants")
public class EtablissementIdentifiantEntity extends AbstractEntity {

    @EmbeddedId
    private EtablissementIdentifiantPK pk;

    @MapsId("uai")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uai", columnDefinition = "BPCHAR(8)", foreignKey = @ForeignKey(name = "fk_identifiants_etablissements"), nullable = false)
    private EtablissementEntity etablissement;

    public EtablissementIdentifiantEntity(@NonNull EtablissementEntity etablissement, @NonNull String clef, @NonNull String valeur) {
        this.etablissement = etablissement;
        this.pk = new EtablissementIdentifiantPK(etablissement.getUai(), clef, valeur);
    }
}
