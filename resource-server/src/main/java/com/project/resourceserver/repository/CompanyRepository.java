package com.project.resourceserver.repository;

import com.project.resourceserver.model.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    public Company findByName(String name);
}