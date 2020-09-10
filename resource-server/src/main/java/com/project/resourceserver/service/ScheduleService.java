package com.project.resourceserver.service;

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

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleService(JobRepository jobRepository, GeoPropertyRepository geoPropertyRepository,
            TechnicianRepository technicianRepository, ScheduleRepository scheduleRepository) {
        this.jobRepository = jobRepository;
        this.geoPropertyRepository = geoPropertyRepository;
        this.technicianRepository = technicianRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public ResponseEntity<String> addJobToSchedule(String technicianId, String geoPropertyId, Job job) {
        HttpHeaders httpHeaders = new HttpHeaders();
        GeoProperty geoProperty = this.geoPropertyRepository.findById(geoPropertyId).get();
        Technician technician = this.technicianRepository.findById(technicianId).get();
        Schedule schedule = technician.getSchedule();

        // Create Job
        // job.setProperty(geoProperty);
        // job.setSchedule(schedule);
        job = this.jobRepository.save(job);

        // Add Job to Schedule
        schedule.addJob(job);

        // Save Schedule
        this.scheduleRepository.save(schedule);
        // this.technicianRepository.save(technician);

        return new ResponseEntity<>("SUCCCESS!", httpHeaders, HttpStatus.CREATED);
    }

}