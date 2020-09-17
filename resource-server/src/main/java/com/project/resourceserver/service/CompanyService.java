package com.project.resourceserver.service;

import java.lang.reflect.Field;
import java.util.Map;

import com.project.resourceserver.model.Account;
import com.project.resourceserver.model.Company;
import com.project.resourceserver.repository.AccountRepository;
import com.project.resourceserver.repository.CompanyRepository;

import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private AccountRepository accountRepository;

    public CompanyService() {
    }

    public ResponseEntity<String> addNewCompany(Company company, String email) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Account existingAccount = accountRepository.findByUsername(email);
        String returnMessage = "";

        if (existingAccount == null) {
            return new ResponseEntity<>("Email not found.", httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>(returnMessage, httpHeaders, HttpStatus.OK);

    }

    public ResponseEntity<Company> findCompanyByemail(String email) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Account account = accountRepository.findByUsername(email);

        if (account == null) {
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }

        Company company = account.getCompany();

        return new ResponseEntity<>(company, httpHeaders, HttpStatus.OK);

    }

    // TODO: This is a patch example.
    // @RequestMapping(value = "/manager/{id}", method = RequestMethod.PATCH)
    // public @ResponseBody void saveManager(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
    //     Manager manager = someServiceToLoadManager(id);
    //     // Map key is field name, v is value
    //     fields.forEach((k, v) -> {
    //         // use reflection to get field k on manager and set it to value v
    //         Field field = ReflectionUtils.findField(Manager.class, k);
    //         field.setAccessible(true);
    //         ReflectionUtils.setField(field, manager, v);
    //     });
    //     managerService.saveManager(manager);
    // }

    public ResponseEntity<Company> update(String companyId, Map<Object, Object> fields) {
        HttpHeaders httpHeaders = new HttpHeaders();
        final Company existingCompany = companyRepository.findById(companyId).get();

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Company.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingCompany, value);
        });
        

        Company returnCompany = companyRepository.save(existingCompany);

        return new ResponseEntity<>(returnCompany, httpHeaders, HttpStatus.OK);
    }

}