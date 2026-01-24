package com.guillaumegasnier.education.shell.datasets.etablissements.metadatas;

import com.guillaumegasnier.education.core.validations.etablissements.Effectifs;
import com.guillaumegasnier.education.core.validations.etablissements.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EffectifsSuperieurDataset implements Effectifs, Metadata, Cloneable {

    private Integer annee;

    private String uai;

    private Integer effectifs = 0;

    public EffectifsSuperieurDataset(Integer anne, String uai, String effectifs) {
        this.annee = anne;
        this.uai = uai;

        if (effectifs != null && !effectifs.isBlank())
            this.effectifs = (int) Double.parseDouble(effectifs);
    }

    public EffectifsSuperieurDataset cloneWithUai(String uai) {
        try {
            EffectifsSuperieurDataset copy = (EffectifsSuperieurDataset) this.clone();
            copy.setUai(uai);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

}
