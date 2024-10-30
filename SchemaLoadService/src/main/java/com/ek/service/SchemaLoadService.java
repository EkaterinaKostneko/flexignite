package com.ek.service;


import com.ek.SqlFieldTypes;
import com.ek.dto.TableFields;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchemaLoadService {

    private final IgniteCache<Long, Object> igniteCache;

    public void createSchema(Map<String, Map<String, TableFields>> models) {

        Map<String, TableFields> schemas = models.get("schemas");

        for (Map.Entry<String, TableFields> stringTableFieldsEntry : schemas.entrySet()) {

            String tableName = stringTableFieldsEntry.getKey();
            TableFields fields = stringTableFieldsEntry.getValue();

            String query = String.format("CREATE TABLE IF NOT EXISTS %s (id bigint primary key, %s)", tableName, createFieldsQuery(fields));

            log.warn("QUERY to SAVE - {}", query);

            igniteCache.query(new SqlFieldsQuery(query)).getAll();
        }
    }
    private String createFieldsQuery(TableFields tableFields) {
        return tableFields
            .getProperties()
            .entrySet()
            .stream()
            .map(entry -> entry.getKey() + " " + SqlFieldTypes.fromField(entry.getValue()))
            .collect(Collectors.joining(", "));
    }
}
