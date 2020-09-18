package com.project.resourceserver.controller;

import com.project.resourceserver.model.Schedule;
import com.project.resourceserver.model.Technician;
import com.project.resourceserver.repository.TechnicianRepository;
import com.project.resourceserver.service.TechnicianService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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


    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    public TechnicianController(){}

    public TechnicianController(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    // GET ****************************************************************************************
    @GetMapping(value="/technician/{technicianId}/schedule")
    public ResponseEntity<Schedule> getScheduleByTechnicianId(@PathVariable String technicianId) {
        return technicianService.findScheduleByTechId(technicianId);
    }

    @GetMapping(value="/technician/{technicianId}")
    public ResponseEntity<Technician> getTechnicianById(@PathVariable String technicianId) {
        return technicianService.findTechById(technicianId);   
    }

    // POST ***************************************************************************************
    @PostMapping(value="/company/{companyId}/technician")
    public ResponseEntity<Technician> createNewTechnician(@RequestBody Technician technician, @PathVariable String companyId) {
        return technicianService.insertNewTechnician(technician, companyId);
    }


    //patch ***************************************************************************************
    @PatchMapping(value="/technician/{technicianId}/schedule/{scheduleId}")
    public ResponseEntity<String> assignScheduleToTechnician(@PathVariable String technicianId, @PathVariable String scheduleId) {
        return technicianService.assignShcedule(technicianId, scheduleId);
    }

    
}