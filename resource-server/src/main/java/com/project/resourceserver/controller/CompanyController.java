package com.project.resourceserver.controller;

import com.project.resourceserver.model.Account;
import com.project.resourceserver.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class CompanyController {

    @Autowired
    private AccountRepository accountRepository;


    @PostMapping(value="/public/company/create/email={email}")
    public ResponseEntity<String> createNewCompany(@PathVariable String email) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Account existingAccount = accountRepository.findByUsername(email);
        String returnMessage = "";
        
        if(existingAccount == null) {
            return new ResponseEntity<>("Email not found.", httpHeaders, HttpStatus.OK);
        }

        
        return new ResponseEntity<>(returnMessage, httpHeaders, HttpStatus.OK);
    }
}