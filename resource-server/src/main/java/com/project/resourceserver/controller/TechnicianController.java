package com.project.resourceserver.controller;

import com.project.resourceserver.model.Technician;
import com.project.resourceserver.repository.TechnicianRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resource")
public class TechnicianController {

    @Autowired 
    TechnicianRepository technicianRepository;

    public TechnicianController(){}

    public TechnicianController(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    @PostMapping(value="/public/technician/add")
    public ResponseEntity<Technician> createNewTechnician(@RequestBody Technician technician) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Technician newTechnician = this.technicianRepository.save(technician);

        if(newTechnician != null)
            return new ResponseEntity<>(newTechnician, httpHeaders, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }
    
}