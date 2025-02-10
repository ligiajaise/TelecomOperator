package com.telecom.operator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhoneNumber {

    private Long id;

    private String phoneNo;

    private boolean isActive;

    public PhoneNumber(Long id, String phoneNo, boolean active) {
        this.phoneNo=phoneNo;
        this.isActive=active;
    }

}
