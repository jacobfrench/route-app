package com.project.resourceserver.repository;

import com.project.resourceserver.model.Job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
    
}