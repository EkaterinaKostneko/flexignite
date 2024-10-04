package com.ek.controller;

import com.ek.dto.Schema;
import com.ek.dto.TableFields;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schemas")
public class SchemaLoadController {

    @PostMapping
    public ResponseEntity<String> load(@RequestBody Map<String, Map<String, TableFields>> models) {

        System.out.println(models);

        return ResponseEntity.ok("GOOD");
    }


}
