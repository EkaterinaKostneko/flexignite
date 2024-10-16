package com.ek.controller;

import com.ek.dto.Schema;
import com.ek.dto.TableFields;
import com.ek.service.SchemaLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schemas")
@RequiredArgsConstructor
public class SchemaLoadController {
    private final SchemaLoadService schemaLoadService;
    @PostMapping
    public ResponseEntity<String> load(@RequestBody Map<String, Map<String, TableFields>> models) {

        schemaLoadService.createSchema(models);

        System.out.println(models);

        return ResponseEntity.ok("GOOD");
    }


}
