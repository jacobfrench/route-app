package com.project.resourceserver.repository;

import com.project.resourceserver.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByUsername(String username);
    
}