package com.project.resourceserver.service;

import java.util.HashSet;
import java.util.Set;

import com.project.resourceserver.model.Company;
import com.project.resourceserver.model.Customer;
import com.project.resourceserver.model.GeoProperty;
import com.project.resourceserver.model.ServiceType;
import com.project.resourceserver.model.Tag;
import com.project.resourceserver.repository.CompanyRepository;
import com.project.resourceserver.repository.CustomerRepository;
import com.project.resourceserver.repository.GeoPropertyRepository;
import com.project.resourceserver.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GeoPropertyService {

    @Autowired
    private GeoPropertyRepository geoPropertyRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<GeoProperty> addNewGeoProperty(Long companyId, Long customerId, Long serviceTypeId, GeoProperty geoProperty) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Customer customer = customerRepository.findById(customerId).get();
        geoProperty.setOwner(customer);

        //Do not add duplicate tags to the database.
        Set<Tag> tags = new HashSet<>();
        for(Tag tag : geoProperty.getTags()) {
            Tag foundTag = this.tagRepository.findByLabel(tag.getLabel());
            if(foundTag == null) {
                tags.add(tag);
            } else {
                tags.add(foundTag);
            }
        }

        geoProperty.removeTags();
        geoProperty.setTags(tags);


        Company company = this.companyRepository.findById(companyId).get();
        Set<Tag> companyTags = company.getTags();
        boolean tagFound;
        for(Tag gt : geoProperty.getTags()) {
            tagFound = false;
            for(Tag ct : companyTags) {
                if(gt.getLabel().equals(ct.getLabel())) {
                    tagFound = true;
                    break;
                }
                   
            }

            if(!tagFound)
                company.addTag(gt);
                
        }

        // Add a ServiceType to the new GeoProperty
        Set<ServiceType> serviceTypes = company.getServiceTypes();
        for(ServiceType st : serviceTypes){
            if(st.getId().equals(serviceTypeId)){
                geoProperty.setServiceType(st);
                break;
            }
        }

        geoProperty = this.geoPropertyRepository.save(geoProperty);
        company.addGeoProperty(geoProperty);
        this.companyRepository.save(company);
        
        if(geoProperty != null)
            return new ResponseEntity<>(geoProperty, httpHeaders, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
    }

}