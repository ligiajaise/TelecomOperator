package com.telecom.operator.controller;

import com.telecom.operator.dto.PhoneNumberDto;
import com.telecom.operator.model.mapper.ModelMapper;
import com.telecom.operator.service.TelecomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TelecomController
{

    @Autowired
    private TelecomService telecomService;

    /**
     *
     * @returns List of Phone Numbers
     */
    @GetMapping("/phones")
    public List<PhoneNumberDto> getAllPhoneNumbers()
    {
        return ModelMapper.convertPhoneModelToDto(telecomService.getAllPhoneNumbers());
    }

    /**
     *
     * @param customerId
     * @return List of Phone Numbers for a given customer
     *         404 in case there are no phones found for the customer
     */
    @GetMapping("/phones/{customerId}")
    public ResponseEntity<List<PhoneNumberDto>> getPhoneNumbersByCustomerId(@PathVariable(value = "customerId") String customerId)
    {
        List<PhoneNumberDto> phones = ModelMapper.convertPhoneModelToDto(telecomService.getPhoneNumberByCustomerId(customerId));
        if (phones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(phones);
    }

    /**
     * @param custId
     * @param phoneNo
     * @return Activates the phone number of a customer and returns the status message
     */
    @PutMapping("/phones/{customerId}/activate/{phoneNo}")
    public ResponseEntity<String> activatePhoneNumber(@PathVariable(value = "customerId") String custId, @PathVariable(value = "phoneNo") String phoneNo){
        boolean success = telecomService.activatePhoneNumber(custId,phoneNo);
        if (success) {
            return ResponseEntity.ok("Phone number " + phoneNo + " activated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Phone number " + phoneNo + " not found.");
        }

    }
}
