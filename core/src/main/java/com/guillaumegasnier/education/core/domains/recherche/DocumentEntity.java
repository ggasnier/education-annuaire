package com.guillaumegasnier.education.core.domains.recherche;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;

@Data
@Document(indexName = "documents")
public class DocumentEntity {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String docId;

    /**
     * etablisements
     * diplomes
     * formations
     * metiers
     * certifications
     */
    @Field(type = FieldType.Keyword)
    private String docType;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String nom;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;

    // nature (pour etablissements)
    @Field(type = FieldType.Keyword)
    private String codeNature;

    @MultiField(
            mainField = @Field(type = FieldType.Text, analyzer = "standard"),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String nomNature;

    // contrat (pour etablissements)
    @Field(type = FieldType.Keyword)
    private String codeContrat;

    @MultiField(
            mainField = @Field(type = FieldType.Text, analyzer = "standard"),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String nomContrat;

    // commune (valide pour tous les types)
    @Field(type = FieldType.Keyword)
    private String codeCommune;

    @MultiField(
            mainField = @Field(type = FieldType.Text, analyzer = "standard"),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String nomCommune;

    @Field(type = FieldType.Date)
    private LocalDateTime createdAt;

}
