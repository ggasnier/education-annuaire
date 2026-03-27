package com.guillaumegasnier.education.core.validations.etablissements;

public interface Effectifs {

    default Integer getEffectifs() {
        return null;
    }

    void setEffectifs(Integer effectifs);
}
