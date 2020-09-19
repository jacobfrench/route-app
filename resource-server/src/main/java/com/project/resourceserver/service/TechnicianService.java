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

  public TechnicianService() {}

  public ResponseEntity<Schedule> findScheduleByTechId(String technicianId) {
    HttpHeaders httpHeaders = new HttpHeaders();
    Technician technician = this.technicianRepository.findById(technicianId).get();

    if (technician != null)
      return new ResponseEntity<>(technician.getSchedule(), httpHeaders, HttpStatus.OK);
    
    return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
  }

  public Technician findTechById(String technicianId) {
    return this.technicianRepository.findById(technicianId).get();
  }

  public Technician insertNewTechnician(Technician technician, String companyId) {
    Schedule newSchedule = this.scheduleRepository.save(new Schedule());
    Company company = this.companyRepository.findById(companyId).get();
    newSchedule.setTechnician(technician);
    technician.setSchedule(newSchedule);
    technician.setEmployer(company);

    return technicianRepository.save(technician);
  }

  // public ResponseEntity<String> assignShcedule(String technicianId, String scheduleId) {
  //   return scheduleService.saveSchedule(technicianId, scheduleId);
  // }

}