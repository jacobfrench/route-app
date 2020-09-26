package com.project.resourceserver.controller;

import java.util.Map;

import com.project.resourceserver.model.Company;
import com.project.resourceserver.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }


    @PostMapping(value="/public/account/{accountEmail}/company")
    public ResponseEntity<Company> postNewCompany(@PathVariable String accountEmail, @RequestBody Company company) {
        HttpHeaders httpHeaders = new HttpHeaders();
        
        Company newCompany = companyService.addNew(accountEmail, company);

        if (newCompany.getId() == null) {
            httpHeaders.add("message", "This account already has a company.");
            return new ResponseEntity<>(company, httpHeaders, HttpStatus.ACCEPTED);
        }
            
        httpHeaders.add("message", "Company was created.");
        return new ResponseEntity<>(newCompany, httpHeaders, HttpStatus.OK);

    }

    @GetMapping("/public/account/{accountEmail}/company")
    public ResponseEntity<Company> getCompanyByEmail(@PathVariable String accountEmail) {
        HttpHeaders httpHeaders = new HttpHeaders();

        Company company = companyService.findCompanyByEmail(accountEmail);

        if(company == null) {
            httpHeaders.add("message", "This account doesn't have a company yet.");
            return new ResponseEntity(null, httpHeaders, HttpStatus.ACCEPTED);
        }

        httpHeaders.add("message", "Company was found.");
        return new ResponseEntity(company, httpHeaders, HttpStatus.OK);
    }

    @PatchMapping("/public/company/{companyId}/update")
    public ResponseEntity<Company> updateCompany(@PathVariable Long companyId, @RequestBody Map<Object, Object> fields) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Company company = companyService.update(companyId, fields);

        if(company.getId() == null){
            httpHeaders.add("message", "Company does not exist.");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
        }

        httpHeaders.add("message", "Company has been updated.");
        return new ResponseEntity<>(company, httpHeaders, HttpStatus.OK);
    }
}