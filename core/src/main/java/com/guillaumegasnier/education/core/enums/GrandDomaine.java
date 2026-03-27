package com.guillaumegasnier.education.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GrandDomaine {

    A("Agriculture et Pêche, Espaces naturels et Espaces verts, Soins aux animaux"),
    B("Arts et Façonnage d'ouvrages d'art"),
    C("Banque, Assurance, Immobilier"),
    D("Commerce, Vente et Grande distribution"),
    E("Communication, Média et Multimédia"),
    F("Construction, Bâtiment et Travaux publics"),
    G("Hôtellerie-Restauration, Tourisme, Loisirs et Animation"),
    H("Industrie"),
    I("Installation et Maintenance"),
    J("Santé"),
    K("Services à la personne et à la collectivité"),
    L("Spectacle"),
    M("Support à l'entreprise"),
    N("Transport et Logistique");

    private final String nom;
}
