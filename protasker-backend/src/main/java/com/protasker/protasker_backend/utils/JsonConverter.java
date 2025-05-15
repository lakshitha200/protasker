package com.protasker.protasker_backend.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class JsonConverter implements AttributeConverter<List<String>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> skills) {
        try {
            return objectMapper.writeValueAsString(skills);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting skills list to JSON", e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String json) {
        try {
            if(json!= null){
                return objectMapper.readValue(json, new TypeReference<List<String>>() {});
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting JSON to skills list", e);
        }
    }
}
