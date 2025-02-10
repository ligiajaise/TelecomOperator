package com.telecom.operator.service;

import com.telecom.operator.model.Customer;
import com.telecom.operator.model.PhoneNumber;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

/**
 * TelecomServiceImpl implements methods required to retrive phone nos , activate phone no.
 */
@Service("TelecomService")
public class TelecomServiceImpl implements TelecomService{

    private static final Map<String, Customer> customerPhoneData = new HashMap<>();

    static {

        List<PhoneNumber> customer1Numbers = new ArrayList<>();
        customer1Numbers.add(new PhoneNumber(1L,"9868689900", true));
        customer1Numbers.add(new PhoneNumber(2L,"9868689911", false));

        List<PhoneNumber> customer2Numbers = new ArrayList<>();
        customer2Numbers.add(new PhoneNumber(3L,"9868689922", false));

        customerPhoneData.put("1", new Customer("1", "customer1",customer1Numbers));
        customerPhoneData.put("2", new Customer("2", "customer2",customer2Numbers));
    }

    @Override
    public List<PhoneNumber> getAllPhoneNumbers() {
        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        customerPhoneData.values().forEach(customer -> phoneNumberList.addAll(customer.getPhoneNumbers()));
        return phoneNumberList;
    }

    @Override
    public List<PhoneNumber> getPhoneNumberByCustomerId(String customerId) {
        return Optional.ofNullable(customerPhoneData.get(customerId))
                .map(Customer::getPhoneNumbers)
                .orElse(Collections.emptyList());
    }

    @Override
    public boolean activatePhoneNumber(String customerId,String phoneNumberNo) {
        return  Optional.ofNullable(customerPhoneData.get(customerId))
                .map(Customer::getPhoneNumbers)
                .flatMap(phoneNumbers -> phoneNumbers.stream()
                        .filter(phone -> phone.getPhoneNo().equals(phoneNumberNo))
                        .findFirst())
                .map(phone -> {
                    phone.setActive(true);
                    return true;
                })
                .orElse(false);
    }
}
