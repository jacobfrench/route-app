package com.project.resourceserver.service;

import com.project.resourceserver.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnicianService {

  @Autowired
  private TechnicianRepository TechnicianRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private CompanyRepository companyRepository;

  public TechnicianService() {

  }

  

    
    
}