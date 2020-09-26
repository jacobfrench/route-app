package com.project.resourceserver.controller;

import com.project.resourceserver.model.Tax;
import com.project.resourceserver.service.TaxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @PostMapping(value="/public/company/{companyId}/tax")
    public ResponseEntity<Tax> postNewTax(@PathVariable Long companyId, @RequestBody Tax tax) {
        return taxService.createNewTax(companyId, tax);
    }




    
}