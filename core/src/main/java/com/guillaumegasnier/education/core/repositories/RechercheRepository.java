package com.guillaumegasnier.education.core.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guillaumegasnier.education.core.domains.recherche.ResultatEntity;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Repository
public class RechercheRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public RechercheRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.objectMapper = objectMapper;
    }

    public List<ResultatEntity> getResultats(String q, int page, List<String> categories, MultiValueMap<String, String> facettes) {

        String sql = "SELECT * FROM public.recherche_resultats(:q, :categories, :filtres::jsonb, :page)";

        var params = new MapSqlParameterSource();

        params.addValue("q", q);
        params.addValue("page", page);

        // Tableaux PostgreSQL
        params.addValue("categories",
                (SqlTypeValue) (ps, paramIndex, sqlType, typeName) -> ps.setArray(paramIndex, ps.getConnection().createArrayOf("text", categories.toArray())),
                Types.ARRAY
        );

        // Sérialisation JSONB
        try {
            params.addValue("filtres", objectMapper.writeValueAsString(facettes));
        } catch (JsonProcessingException e) {
            params.addValue("filtres", "{}");
        }

        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> {
            ResultatEntity entity = new ResultatEntity();
            entity.setId((UUID) rs.getObject("id"));
            entity.setNom(rs.getString("nom"));
            entity.setCategorie(rs.getString("categorie"));
            entity.setKey(rs.getString("key"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            entity.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
            entity.setScore(rs.getDouble("score"));

            // Conversion PGobject -> Map
            PGobject pg = (PGobject) rs.getObject("informations");
            if (pg != null && pg.getValue() != null) {
                try {
                    entity.setInformations(objectMapper.readValue(pg.getValue(), new TypeReference<Map<String, String>>() {
                    }));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            return entity;
        });
    }
}
