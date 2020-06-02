package com.project.resourceserver.repository;

import com.project.resourceserver.model.GeoProperty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoPropertyRepository extends JpaRepository<GeoProperty, String> {
    
}