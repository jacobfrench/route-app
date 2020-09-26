package com.project.resourceserver.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.resourceserver.model.Technician;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {
    
}