package com.rfernandon.dataconversion;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.databind.type.TypeFactory.defaultInstance;
import static java.util.Objects.isNull;

public class ObjectMapperUtil {

    public static <T> T convertValue(Object value, Class<T> templateClass) {

        if (isNull(value)) return null;

        var mapper = getObjectMapper();
        return mapper.convertValue(value, templateClass);
    }

    public static <T> List<T> convertValues(Object value, Class<T> templateClass) {

        if (isNull(value)) return List.of();

        var mapper = getObjectMapper();
        var collectionType = defaultInstance().constructCollectionType(List.class, templateClass);
        return mapper.convertValue(value, collectionType);
    }

    public static String convertObjectToJson(Object object) {
        try {
            var mapper = getObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error json processing");
        }
    }

    public static JsonNode convertJsonToJsonNode(String json) {
        try {
            var mapper = getObjectMapper();
            return mapper.readTree(json);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error json processing");
        }
    }

    private static ObjectMapper getObjectMapper() {
        var mapper = new ObjectMapper();
        mapper.configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}