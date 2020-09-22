package com.project.resourceserver.service;

import com.project.resourceserver.model.Company;
import com.project.resourceserver.model.GeoProperty;
import com.project.resourceserver.model.Job;
import com.project.resourceserver.model.Schedule;
import com.project.resourceserver.model.Technician;
import com.project.resourceserver.repository.GeoPropertyRepository;
import com.project.resourceserver.repository.JobRepository;
import com.project.resourceserver.repository.ScheduleRepository;
import com.project.resourceserver.repository.TechnicianRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private GeoPropertyRepository geoPropertyRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    // @Autowired
    private CompanyService companyService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleService(JobRepository jobRepository, GeoPropertyRepository geoPropertyRepository,
            TechnicianRepository technicianRepository, ScheduleRepository scheduleRepository, CompanyService companyService) {
        this.jobRepository = jobRepository;
        this.geoPropertyRepository = geoPropertyRepository;
        this.technicianRepository = technicianRepository;
        this.scheduleRepository = scheduleRepository;
        this.companyService = companyService;
    }

    public ResponseEntity<String> addJobToSchedule(String technicianId, String geoPropertyId, Job job) {
        HttpHeaders httpHeaders = new HttpHeaders();
        GeoProperty geoProperty = this.geoPropertyRepository.findById(geoPropertyId).get();
        Technician technician = this.technicianRepository.findById(technicianId).get();
        Schedule schedule = technician.getSchedule();

        // Create Job
        job.setProperty(geoProperty);
        // job.setSchedule(schedule);
        job = this.jobRepository.save(job);

        // Add Job to Schedule
        schedule.addJob(job);

        // Save Schedule
        this.scheduleRepository.save(schedule);
        // this.technicianRepository.save(technician);

        return new ResponseEntity<>("SUCCCESS!", httpHeaders, HttpStatus.CREATED);
    }


    public ResponseEntity<String> saveSchedule(String technicianId, String scheduleId) {
        HttpHeaders httpHeaders = new HttpHeaders();

        Technician technician = this.technicianRepository.findById(technicianId).get();
        Schedule schedule = this.scheduleRepository.findById(scheduleId).get();
    
        schedule.setTechnician(technician);
    
        this.scheduleRepository.save(schedule);
    
        return new ResponseEntity<>("SUCCESS!", httpHeaders, HttpStatus.CREATED);


    }

    public ResponseEntity<Schedule> createNewSchedule(String companyId, String scheduleId) {
        HttpHeaders httpHeaders = new HttpHeaders();

        this.companyService = new CompanyService();

        Company company = companyService.findById(companyId);

        if(company == null)
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);

        
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);

    }

}