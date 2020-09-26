package com.project.resourceserver.service;

import com.project.resourceserver.model.Company;
import com.project.resourceserver.model.Material;
import com.project.resourceserver.repository.CompanyRepository;
import com.project.resourceserver.repository.MaterialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository, CompanyRepository companyRepository) {
        this.materialRepository = materialRepository;
        this.companyRepository = companyRepository;
    }

    public ResponseEntity<Material> addNewMaterial(Long companyId, Material material) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Material newMaterial = this.materialRepository.save(material);
        Company company = this.companyRepository.findById(companyId).get();

        company.addNewMaterial(newMaterial);
        companyRepository.save(company);

        
        if(newMaterial != null)
            return new ResponseEntity<>(newMaterial, httpHeaders, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
    }
    
}