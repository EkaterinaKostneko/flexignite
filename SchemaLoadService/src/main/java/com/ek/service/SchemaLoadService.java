package com.ek.service;


import com.ek.SqlFieldTypes;
import com.ek.dto.Field;
import com.ek.dto.TableFields;
import lombok.RequiredArgsConstructor;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchemaLoadService {

    private final ClientConfiguration clientConfiguration;

    public void createSchema(Map<String, Map<String, TableFields>> models) {
        try (IgniteClient client = Ignition.startClient(clientConfiguration)) {
            ClientCache<Integer, String> igniteCache = client.cache("myCache");

            Map<String, TableFields> schemas = models.get("schemas");
            for (Map.Entry<String, TableFields> stringTableFieldsEntry : schemas.entrySet()) {
                String tableName = stringTableFieldsEntry.getKey();
                TableFields fields = stringTableFieldsEntry.getValue();

                igniteCache.query(new SqlFieldsQuery("CREATE TABLE ? (?)").setArgs(tableName, createFieldsQuery(fields))).getAll();
            }
            System.out.println(igniteCache.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
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
