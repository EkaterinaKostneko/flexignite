package com.ek.service;


import com.ek.dto.TableFields;
import lombok.RequiredArgsConstructor;
import org.apache.ignite.IgniteCache;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SchemaLoadService {
    private final IgniteCache<?,?> igniteCache;
    public void createSchema(Map<String, Map<String, TableFields>> models){
        Map<String, TableFields> schemas = models.get("schemas");
        for (Map.Entry<String, TableFields> stringTableFieldsEntry : schemas.entrySet()) {
            String key = stringTableFieldsEntry.getKey();
            TableFields fields = stringTableFieldsEntry.getValue();

        }


    }

}
