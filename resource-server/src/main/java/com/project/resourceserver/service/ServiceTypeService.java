package com.project.resourceserver.service;


import com.project.resourceserver.model.Company;
import com.project.resourceserver.model.ServiceType;
import com.project.resourceserver.repository.CompanyRepository;
import com.project.resourceserver.repository.ServiceTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;


@Service
public class ServiceTypeService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeService(){}

    public ResponseEntity<ServiceType> createNewServiceType(String companyId, ServiceType serviceType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        ServiceType newServiceType = this.serviceTypeRepository.save(serviceType);
        Company company = this.companyRepository.findById(companyId).get();
        company.addNewServiceType(newServiceType);
        this.companyRepository.save(company);
        
        if(serviceType != null)
            return new ResponseEntity<>(serviceType, httpHeaders, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
    }

    
    
    
}