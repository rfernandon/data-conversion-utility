package com.rfernandon.dataconversion.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPhoneDestination implements Serializable {

    private Integer areaCode;
    private Integer regionCode;
    private Long number;
    private Boolean validate;
}