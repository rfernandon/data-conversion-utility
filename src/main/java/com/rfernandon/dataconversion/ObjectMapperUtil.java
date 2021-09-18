package com.rfernandon.dataconversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

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
        var collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, templateClass);
        return mapper.convertValue(value, collectionType);
    }

    public static String objectToJson(Object object) throws JsonProcessingException {
        var mapper = getObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static JsonNode convertJsonNode(String json) throws JsonProcessingException {
        var mapper = getObjectMapper();
        return mapper.readTree(json);
    }

    private static ObjectMapper getObjectMapper() {
        var mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}