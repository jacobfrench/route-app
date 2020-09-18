package com.project.resourceserver.service;

import com.project.resourceserver.model.Company;
import com.project.resourceserver.model.Schedule;
import com.project.resourceserver.model.Technician;
import com.project.resourceserver.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TechnicianService {

  @Autowired
  private TechnicianRepository technicianRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private CompanyRepository companyRepository;

  public TechnicianService() {

  }

  public ResponseEntity<Schedule> findScheduleByTechId(String technicianId) {
    HttpHeaders httpHeaders = new HttpHeaders();
    Technician technician = this.technicianRepository.findById(technicianId).get();

    if (technician != null)
      return new ResponseEntity<>(technician.getSchedule(), httpHeaders, HttpStatus.OK);
    else
      return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
  }

  public ResponseEntity<Technician> findTechById(String technicianId) {
    HttpHeaders httpHeaders = new HttpHeaders();
    Technician technician = this.technicianRepository.findById(technicianId).get();

    if (technician != null)
      return new ResponseEntity<>(technician, httpHeaders, HttpStatus.OK);
    else
      return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<Technician> insertNewTechnician(Technician technician, String companyId) {
    HttpHeaders httpHeaders = new HttpHeaders();
    Schedule newSchedule = this.scheduleRepository.save(new Schedule());
    Company company = this.companyRepository.findById(companyId).get();
    newSchedule.setTechnician(technician);
    technician.setSchedule(newSchedule);
    technician.setEmployer(company);

    // this.bCryptPasswordEncoder = new BCryptPasswordEncoder();

    // String encryptedPassword =
    // bCryptPasswordEncoder.encode(technician.getPassword());
    // technician.setPassword(encryptedPassword);

    Technician newTechnician = this.technicianRepository.save(technician);

    if (newTechnician != null)
      return new ResponseEntity<>(newTechnician, httpHeaders, HttpStatus.CREATED);
    else
      return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
  }

  public ResponseEntity<String> assignShcedule(String technicianId, String scheduleId) {
    HttpHeaders httpHeaders = new HttpHeaders();

    Technician technician = this.technicianRepository.findById(technicianId).get();
    Schedule schedule = this.scheduleRepository.findById(scheduleId).get();

    schedule.setTechnician(technician);

    this.scheduleRepository.save(schedule);

    return new ResponseEntity<>("SUCCESS!", httpHeaders, HttpStatus.CREATED);
  }

}