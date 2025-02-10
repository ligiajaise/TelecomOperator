package com.telecom.operator.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhoneNumberDto {

    private String phoneNo;

    private boolean isActive;


    public PhoneNumberDto(String phoneNo, boolean active) {
        this.phoneNo=phoneNo;
        this.isActive=active;
    }
}
