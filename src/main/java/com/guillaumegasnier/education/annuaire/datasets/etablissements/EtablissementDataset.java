package com.guillaumegasnier.education.annuaire.datasets.etablissements;

import com.guillaumegasnier.education.annuaire.enums.EtatEtablissement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class EtablissementDataset {

    protected String uai;
    protected String siret;
    protected String nom;

    protected String adresse;
    protected String complement;
    protected String codePostal;
    protected String codeCommune;
    protected String codePays;

    protected String codeNature;
    protected String codeContrat;

    protected EtatEtablissement etat;

    protected String contactTelephone;
    protected String contactMail;
    protected String contactWeb;

    protected List<ContactEtablissementDataset> contacts = new ArrayList<>();

}
