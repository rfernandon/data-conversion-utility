package com.rfernandon.dataconversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rfernandon.dataconversion.model.UserPhoneDestination;
import com.rfernandon.dataconversion.model.UserPhoneSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rfernandon.dataconversion.ObjectMapperUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("\uD83C\uDF7A Testando a class ObjectMapperUtil")
class ObjectMapperUtilTest {

    @Test
    @DisplayName("Should convert source object to destination")
    public void shouldConvertSourceObjectToDestination() {

        var userPhoneSource = getUserPhoneSource();
        var userPhoneDestination = convertValue(userPhoneSource, UserPhoneDestination.class);

        assertAll(
                () -> assertEquals(userPhoneSource.getAreaCode(), userPhoneDestination.getAreaCode()),
                () -> assertEquals(userPhoneSource.getRegionCode(), userPhoneDestination.getRegionCode()),
                () -> assertEquals(userPhoneSource.getNumber(), userPhoneDestination.getNumber()),
                () -> assertNull(userPhoneDestination.getValidate())
        );
    }

    @Test
    @DisplayName("Should convert source object list to destination")
    public void shouldConvertSourceObjectListToDestination() {

        var userPhoneSource = getUserPhoneSource();
        var userPhoneSourceList = List.of(userPhoneSource);
        var userPhoneDestinationList = convertValues(userPhoneSourceList, UserPhoneDestination.class);

        assertEquals(1, userPhoneDestinationList.size());

        var userPhoneDestination = userPhoneDestinationList.get(0);
        assertAll(
                () -> assertEquals(userPhoneSource.getAreaCode(), userPhoneDestination.getAreaCode()),
                () -> assertEquals(userPhoneSource.getRegionCode(), userPhoneDestination.getRegionCode()),
                () -> assertEquals(userPhoneSource.getNumber(), userPhoneDestination.getNumber()),
                () -> assertNull(userPhoneDestination.getValidate())
        );
    }

    @Test
    @DisplayName("Should convert source object to JsonNode")
    public void shouldConvertSourceObjectToJsonNode() throws JsonProcessingException {

        var json = "{\"userId\":\"123\",\"areaCode\":55,\"regionCode\":11,\"number\":996571234}";
        var userPhoneJsonNode= convertJsonNode(json);

        assertAll(
                () -> assertEquals("123", userPhoneJsonNode.findValue("userId").asText()),
                () -> assertEquals(55, userPhoneJsonNode.findValue("areaCode").asInt()),
                () -> assertEquals(11, userPhoneJsonNode.findValue("regionCode").asInt()),
                () -> assertEquals(996571234, userPhoneJsonNode.findValue("number").asLong())
        );
    }

    @Test
    @DisplayName("Should convert source object to json string")
    public void shouldConvertSourceObjectToJsonString() throws JsonProcessingException {

        var userPhoneSource= objectToJson(getUserPhoneSource());
        assertEquals("{\"userId\":\"123\",\"areaCode\":55,\"regionCode\":11,\"number\":996571234}", userPhoneSource);
    }

    private UserPhoneSource getUserPhoneSource() {
        return UserPhoneSource.builder()
                .userId("123")
                .areaCode(55)
                .regionCode(11)
                .number(996571234L)
                .build();
    }
}