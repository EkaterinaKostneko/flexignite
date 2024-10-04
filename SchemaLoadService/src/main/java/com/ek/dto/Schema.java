package com.ek.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Schema {

    List<Map<String, TableFields>> models;

}
