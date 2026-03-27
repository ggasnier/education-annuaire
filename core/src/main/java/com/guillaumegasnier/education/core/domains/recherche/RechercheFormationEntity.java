package com.guillaumegasnier.education.core.domains.recherche;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "formations")
public class RechercheFormationEntity {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nom;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String objectif;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String resultats;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String contenu;

    @Field(type = FieldType.Keyword)
    private String codeCertifiante;
    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nomCertifiante;

    @Field(type = FieldType.Keyword)
    private String codeCertification;
    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nomCertification;

}
