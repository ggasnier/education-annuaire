package com.guillaumegasnier.education.web.configuration;

@FunctionalInterface
public interface NomResolver {
    String resolveNom(String code);
}
