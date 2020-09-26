package com.project.resourceserver.repository;

import com.project.resourceserver.model.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public Tag findByLabel(String label);
    
}