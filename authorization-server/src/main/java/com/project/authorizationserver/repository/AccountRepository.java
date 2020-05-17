package com.project.authorizationserver.repository;

import java.util.Optional;

import com.project.authorizationserver.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional <Account> findByUsername(String username);
    
}