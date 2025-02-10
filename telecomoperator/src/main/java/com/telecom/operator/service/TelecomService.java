package com.telecom.operator.service;

import com.telecom.operator.model.PhoneNumber;

import java.util.List;

public interface TelecomService {
        List<PhoneNumber> getAllPhoneNumbers();
        List<PhoneNumber> getPhoneNumberByCustomerId(String customerId);
        boolean activatePhoneNumber(String customerId, String phoneNumber) ;
 }
