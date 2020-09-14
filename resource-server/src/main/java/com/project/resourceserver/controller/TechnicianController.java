package com.project.resourceserver.controller;

import com.project.resourceserver.model.Company;
import com.project.resourceserver.model.Schedule;
import com.project.resourceserver.model.Technician;
import com.project.resourceserver.repository.CompanyRepository;
import com.project.resourceserver.repository.ScheduleRepository;
import com.project.resourceserver.repository.TechnicianRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CompanyRepository companyRepository;

    // private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @PostMapping(value="/company/{companyId}/technician")
    public ResponseEntity<Boolean> createNewTechnician(@RequestBody Technician technician, @PathVariable String companyId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Schedule newSchedule = this.scheduleRepository.save(new Schedule());
        Company company = this.companyRepository.findById(companyId).get();
        newSchedule.setTechnician(technician);
        technician.setSchedule(newSchedule);
        technician.setEmployer(company);

        // this.bCryptPasswordEncoder = new BCryptPasswordEncoder();

        // String encryptedPassword = bCryptPasswordEncoder.encode(technician.getPassword());
        // technician.setPassword(encryptedPassword);

        Technician newTechnician = this.technicianRepository.save(technician);
        

        if(newTechnician != null)
            return new ResponseEntity<>(true, httpHeaders, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(false, httpHeaders, HttpStatus.CREATED);
    }


    //PUT ***************************************************************************************** 

    @PatchMapping(value="/technician/{technicianId}/schedule/{scheduleId}")
    public ResponseEntity<String> assignScheduleToTechnician(@PathVariable String technicianId, @PathVariable String scheduleId) {
        HttpHeaders httpHeaders = new HttpHeaders();

        Technician technician = this.technicianRepository.findById(technicianId).get();
        Schedule schedule = this.scheduleRepository.findById(scheduleId).get();

        schedule.setTechnician(technician);

        this.scheduleRepository.save(schedule);

        return new ResponseEntity<>("SUCCESS!", httpHeaders, HttpStatus.CREATED);

    }
    
}