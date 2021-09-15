package com.rfernandon.dataconversion.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPhoneSource implements Serializable {

    private String userId;
    private Integer areaCode;
    private Integer regionCode;
    private Long number;
}