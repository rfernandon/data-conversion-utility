package com.rfernandon.dataconversion;

import com.rfernandon.dataconversion.model.UserPhoneDestination;
import com.rfernandon.dataconversion.model.UserPhoneSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.rfernandon.dataconversion.ObjectMapperUtil.convertValue;
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

    private UserPhoneSource getUserPhoneSource() {
        return UserPhoneSource.builder()
                .userId("123")
                .areaCode(55)
                .regionCode(11)
                .number(996571234L)
                .build();
    }
}