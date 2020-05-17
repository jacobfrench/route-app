package com.project.authorizationserver;

import com.project.authorizationserver.model.Account;
import com.project.authorizationserver.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class AuthorizationServerApplication implements CommandLineRunner {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

		// Account account = new Account();
		// account.setUsername("jakefrench84@gmail.com");
		// account.setPassword(pe.encode("password"));

		// accountRepository.deleteAll();
		// accountRepository.save(account);

		System.out.println("Authorization Server Started Successfully!");
	}


}
