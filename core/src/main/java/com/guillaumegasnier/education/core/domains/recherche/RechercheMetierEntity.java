package com.guillaumegasnier.education.core.domains.recherche;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
@ToString
@Document(indexName = "metiers")
public class RechercheMetierEntity {

    @Id
    private String code;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nom;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String description;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private List<String> appellations;

    @Field(type = FieldType.Keyword)
    private String codeDomaine;
    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nomDomaine;

}
