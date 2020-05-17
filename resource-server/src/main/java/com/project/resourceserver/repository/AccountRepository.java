package com.project.resourceserver.repository;

import com.project.resourceserver.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    public Account findByUsername(String username);
    
}