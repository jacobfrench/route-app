package com.project.resourceserver.controller;

import com.project.resourceserver.model.ServiceType;
import com.project.resourceserver.service.ServiceTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource/public")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @PostMapping("/company/{companyId}/service_type")
    public ResponseEntity<ServiceType> createNewServiceType(@PathVariable Long companyId, @RequestBody ServiceType serviceType) {
        return serviceTypeService.createNewServiceType(companyId, serviceType);
    }

}