package com.project.resourceserver.controller;

import com.project.resourceserver.model.Schedule;
import com.project.resourceserver.model.Technician;
import com.project.resourceserver.repository.ScheduleRepository;
import com.project.resourceserver.repository.TechnicianRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resource/public")
public class TechnicianController {


    @Autowired 
    private TechnicianRepository technicianRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public TechnicianController(){}

    public TechnicianController(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    // GET ****************************************************************************************

    @GetMapping(value="/technician/{technicianId}/schedule")
    public ResponseEntity<Schedule> getScheduleByTechnicianId(@PathVariable String technicianId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Technician technician = this.technicianRepository.findById(technicianId).get();

        if(technician != null)
            return new ResponseEntity<>(technician.getSchedule(), httpHeaders, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        
    }

    @GetMapping(value="/technician/{technicianId}")
    public ResponseEntity<Technician> getTechnicianById(@PathVariable String technicianId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Technician technician = this.technicianRepository.findById(technicianId).get();

        if(technician != null)
            return new ResponseEntity<>(technician, httpHeaders, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        
    }

    // POST ***************************************************************************************

    @PostMapping(value="/technician")
    public ResponseEntity<Technician> createNewTechnician(@RequestBody Technician technician) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Schedule newSchedule = this.scheduleRepository.save(new Schedule());

        newSchedule.setTechnician(technician);
        technician.setSchedule(newSchedule);

        Technician newTechnician = this.technicianRepository.save(technician);

        if(newTechnician != null)
            return new ResponseEntity<>(newTechnician, httpHeaders, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }


    //PUT ***************************************************************************************** 

    @PutMapping(value="/technician/{technicianId}/schedule/{scheduleId}")
    public ResponseEntity<String> assignScheduleToTechnician(@PathVariable String technicianId, @PathVariable String scheduleId) {
        HttpHeaders httpHeaders = new HttpHeaders();

        Technician technician = this.technicianRepository.findById(technicianId).get();
        Schedule schedule = this.scheduleRepository.findById(scheduleId).get();

        schedule.setTechnician(technician);

        this.scheduleRepository.save(schedule);

        return new ResponseEntity<>("SUCCESS!", httpHeaders, HttpStatus.CREATED);

    }
    
}