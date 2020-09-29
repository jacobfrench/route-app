package com.project.resourceserver.controller;

import com.project.resourceserver.model.Customer;
import com.project.resourceserver.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value="/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Customer customer = customerService.findCustomerById(customerId);
        if(customer == null){
            httpHeaders.add("result_msg", "Customer not Found");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);
        }

        String message = String.format("Customer with id=%d was found.", customer.getId());
        httpHeaders.add("result_msg", message);
        return new ResponseEntity<>(customer, httpHeaders, HttpStatus.FOUND);

    }

    @GetMapping(value="/customer/email/{customerEmail}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String customerEmail) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Customer customer = customerService.findCustomerByEmail(customerEmail);
        if(customer == null){
            httpHeaders.add("result_msg", "Customer not Found");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);
        }

        String message = String.format("Customer with id=%d was found.", customer.getId());
        httpHeaders.add("result_msg", message);
        return new ResponseEntity<>(customer, httpHeaders, HttpStatus.FOUND);

    }
    
}