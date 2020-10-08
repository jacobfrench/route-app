package com.project.resourceserver.service;

import java.lang.reflect.Field;
import java.util.Map;

import com.project.resourceserver.constants.enums.ContactPref;
import com.project.resourceserver.model.Company;
import com.project.resourceserver.model.Customer;
import com.project.resourceserver.repository.CompanyRepository;
import com.project.resourceserver.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository, CompanyRepository companyRepository) {
        this.customerRepository = customerRepository;
        this.companyRepository = companyRepository;
    }

    public ResponseEntity<Customer> addNewCustomer(Long companyId, Customer customer) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Company company = companyRepository.findById(companyId).get();
        customer.setCompany(company);
        customer = customerRepository.save(customer);
        
        if(customer != null)
            return new ResponseEntity<>(customer, httpHeaders, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);


    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }

    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer updateCustomer(Long customerId, Map<Object, Object> fields) {
        Customer existingCustomer = customerRepository.findById(customerId).get();

        fields.forEach((key, value) -> {
            if(!key.equals("id")){
                Field field = ReflectionUtils.findRequiredField(Customer.class, (String) key);
                field.setAccessible(true);

                if(key.equals("primePref") || key.equals("altPref")) {
                    if(value.equals("CALL"))
                        value = ContactPref.CALL;
                    else if(value.equals("TEXT"))
                        value = ContactPref.TEXT;
                    else if(value.equals("TEXT_OR_CALL"))
                        value = ContactPref.TEXT_OR_CALL;
                    else if(value.equals("NONE"))
                        value = ContactPref.NONE;
                }

                ReflectionUtils.setField(field, existingCustomer, value);
            }
        });
        
        Customer returnCustomer = customerRepository.save(existingCustomer);

        return returnCustomer;
    }
    
}