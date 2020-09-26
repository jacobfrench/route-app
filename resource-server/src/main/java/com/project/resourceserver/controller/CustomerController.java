package com.project.resourceserver.controller;

import com.project.resourceserver.model.Customer;
import com.project.resourceserver.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource/public")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }

    @PostMapping(value="/company/{companyId}/customer")
    public ResponseEntity<Customer> addNewCustomer(@PathVariable Long companyId, @RequestBody Customer customer) {
        return customerService.addNewCustomer(companyId, customer);
    }
    
}