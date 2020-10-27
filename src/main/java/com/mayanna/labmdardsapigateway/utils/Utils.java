package com.mayanna.labmdardsapigateway.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.io.IOException;

public class Utils {

    public static <T> T convertJsonStringToObject(String json, Class<T> clazz) {
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            System.out.println("OBJECT MAPPING FAILED WITH THE FOLLOWING ERROR " + e.getMessage());
        }
        return null;
    }
}
