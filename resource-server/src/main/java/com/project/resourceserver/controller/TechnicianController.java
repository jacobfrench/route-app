package com.project.resourceserver.controller;

import com.project.resourceserver.model.Technician;
import com.project.resourceserver.repository.TechnicianRepository;
import com.project.resourceserver.service.TechnicianService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resource/private")
public class TechnicianController {
    private static Logger logger = LogManager.getLogger(TechnicianController.class);

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private TechnicianService technicianService;

    public TechnicianController() {
    }

    public TechnicianController(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    // GET ****************************************************************************************

    @GetMapping(value = "/technician/{technicianId}")
    public ResponseEntity<Technician> getTechnicianById(@PathVariable Long technicianId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Technician technician = this.technicianRepository.findById(technicianId).get();

        if (technician != null)
            return new ResponseEntity<>(technician, httpHeaders, HttpStatus.OK);

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);

    }

    // POST ***************************************************************************************
    @PostMapping(value = "/company/{companyId}/technician")
    public ResponseEntity<Technician> createNewTechnician(@RequestBody Technician technician, @PathVariable Long companyId) {
        HttpHeaders httpHeaders = new HttpHeaders();

        Technician newTechnician = technicianService.insertNewTechnician(technician, companyId);

        if (newTechnician != null)
            return new ResponseEntity<>(newTechnician, httpHeaders, HttpStatus.CREATED);
            
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // PATCH *************************************************************************************
    // @PatchMapping(value="/technician/{technicianId}/schedule/{scheduleId}")
    // public ResponseEntity<String> assignScheduleToTechnician(@PathVariable String
    // technicianId, @PathVariable String scheduleId) {
    // return technicianService.assignShcedule(technicianId, scheduleId);
    // }

}