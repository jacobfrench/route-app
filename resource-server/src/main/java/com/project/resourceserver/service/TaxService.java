package com.project.resourceserver.service;

import com.project.resourceserver.model.Company;
import com.project.resourceserver.model.Tax;
import com.project.resourceserver.repository.CompanyRepository;
import com.project.resourceserver.repository.TaxRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TaxRepository taxRepository;

    public TaxService(){}


    public ResponseEntity<Tax> createNewTax(String companyId, Tax tax) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Tax newTax = taxRepository.save(tax);
        Company company = companyRepository.findById(companyId).get();
        company.addNewTax(tax);
        companyRepository.save(company);

        if(newTax != null)
            return new ResponseEntity<>(newTax, httpHeaders, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);

        
        
    }
    
}