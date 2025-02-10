package com.telecom.operator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Customer {

    private String customerId;

    private String customerName;

    private List<PhoneNumber> phoneNumbers;

    public Customer(String customerId,String customerName, List<PhoneNumber> listPhoneNos) {
        this.customerId = customerId;
        this.customerName=customerName;
        this.phoneNumbers=listPhoneNos;
    }
}
