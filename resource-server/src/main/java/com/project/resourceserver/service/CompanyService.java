package com.project.resourceserver.service;

import java.lang.reflect.Field;
import java.util.Map;

import com.project.resourceserver.model.Account;
import com.project.resourceserver.model.Company;
import com.project.resourceserver.repository.AccountRepository;
import com.project.resourceserver.repository.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private AccountRepository accountRepository;

    public CompanyService() {}

    public Company addNew(String email, Company company) {
        Account account = accountRepository.findByUsername(email);
        
        if(account.getCompany() != null) 
            return company; // company id will return null

        Company newCompany = companyRepository.save(company);
        account.setCompany(newCompany);
        accountRepository.save(account);
        
        return newCompany; 
    }

    public Company findCompanyByEmail(String email) {
        Account account = accountRepository.findByUsername(email);
        return account.getCompany();
    }

    public Company update(Long companyId, Map<Object, Object> fields) {
        Company existingCompany = companyRepository.findById(companyId).get();

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Company.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingCompany, value);
        });
        

        Company returnCompany = companyRepository.save(existingCompany);

        return returnCompany;
    }

    public Company findById(Long companyId) {
        return companyRepository.findById(companyId).get();
    }

}