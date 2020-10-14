package com.project.resourceserver.repository;

import com.project.resourceserver.model.GeoLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoLocationRepository extends JpaRepository<GeoLocation, Long> {
    
}