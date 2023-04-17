package com.yaganaste.excercise5.controllers;

import com.yaganaste.excercise5.dtos.CustomerTransferDto;
import com.yaganaste.excercise5.services.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody CustomerTransferDto customerTransferDto) {
        customerService.transfer(customerTransferDto.getValue(), customerTransferDto.getCustomerSenderId(),
                customerTransferDto.getCustomerReceiverId());
    }
}