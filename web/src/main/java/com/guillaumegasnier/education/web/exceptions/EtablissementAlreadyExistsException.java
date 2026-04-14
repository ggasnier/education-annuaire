package com.guillaumegasnier.education.web.exceptions;

import lombok.Getter;

@Getter
public class EtablissementAlreadyExistsException extends RuntimeException {

    private final String uai;

    public EtablissementAlreadyExistsException(String uai) {
        super("Un établissement avec l'UAI '" + uai + "' existe déjà");
        this.uai = uai;
    }
}


