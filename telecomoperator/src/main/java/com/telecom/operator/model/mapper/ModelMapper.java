package com.telecom.operator.model.mapper;

import com.telecom.operator.dto.PhoneNumberDto;
import com.telecom.operator.model.PhoneNumber;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapper {

    public static List<PhoneNumberDto> convertPhoneModelToDto(List<PhoneNumber> phoneNumberList){
       return phoneNumberList.stream().map(phoneNumber ->
                new PhoneNumberDto(phoneNumber.getPhoneNo(),phoneNumber.isActive())).collect(Collectors.toList());

    }

}
