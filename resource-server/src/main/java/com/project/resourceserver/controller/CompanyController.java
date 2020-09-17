package com.project.resourceserver.controller;

import java.util.Map;

import com.project.resourceserver.model.Company;
import com.project.resourceserver.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping(value="/public/company/?email={email}")
    public ResponseEntity<String> postNewCompany(@PathVariable String email, @RequestBody Company company) {
        return companyService.addNewCompany(company, email);
    }


    @GetMapping("/public/company/?email={email}")
    public ResponseEntity<Company> getCompanyByemail(@PathVariable String email) {
        return companyService.findCompanyByemail(email);
    }

    @PatchMapping("/public/company/{companyId}/update")
    public ResponseEntity<Company> updateCompany(@PathVariable String companyId, @RequestBody Map<Object, Object> fields) {
        return companyService.update(companyId, fields);
    }
}