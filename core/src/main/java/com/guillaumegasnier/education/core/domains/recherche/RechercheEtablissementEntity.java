package com.guillaumegasnier.education.core.domains.recherche;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "etablissements")
public class RechercheEtablissementEntity {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nom;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String description;

    @Field(type = FieldType.Keyword)
    private String codeSecteur;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nomSecteur;

    @Field(type = FieldType.Keyword)
    private String codeNature;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nomNature;

    @Field(type = FieldType.Keyword)
    private String codeCommune;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nomCommune;

    @Field(type = FieldType.Keyword)
    private String codeDepartement;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nomDepartement;

    @Field(type = FieldType.Keyword)
    private String codeAcademie;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nomAcademie;

    @Field(type = FieldType.Keyword)
    private String codeRegion;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nomRegion;

    @Field(type = FieldType.Keyword)
    private String codePays;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nomPays;
}
