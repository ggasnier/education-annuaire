package com.guillaumegasnier.education.core.domains.recherche;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(indexName = "etablissements")
public class RechercheEtablissementEntity {

    @Id
    private String uai;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String nom;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String adresse;

    @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french")
    private String codePostal;

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

    @Field(type = FieldType.Nested)
    private List<RechercheOption> options = new ArrayList<>();

    @Field(type = FieldType.Nested)
    private List<RechercheLangue> langues = new ArrayList<>();

    public record RechercheOption(
            @Field(type = FieldType.Keyword) String codeOption,
            @MultiField(
                    mainField = @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french"),
                    otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
            ) String nomOption
    ) {
    }

    public record RechercheLangue(
            @Field(type = FieldType.Keyword) String codeLangue,
            @MultiField(
                    mainField = @Field(type = FieldType.Text, analyzer = "french", searchAnalyzer = "french"),
                    otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
            ) String nomLangue
    ) {
    }
}
