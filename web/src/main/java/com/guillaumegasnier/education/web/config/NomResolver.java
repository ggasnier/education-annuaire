package com.guillaumegasnier.education.web.config;

@FunctionalInterface
public interface NomResolver {
    String resolveNom(String code);
}
