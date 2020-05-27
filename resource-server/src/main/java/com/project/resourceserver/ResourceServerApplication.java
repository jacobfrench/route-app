package com.project.resourceserver;

import com.project.resourceserver.model.Account;
import com.project.resourceserver.model.Company;
import com.project.resourceserver.model.Route;
import com.project.resourceserver.repository.AccountRepository;
import com.project.resourceserver.repository.CompanyRepository;
import com.project.resourceserver.repository.RouteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
public class ResourceServerApplication implements CommandLineRunner{

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


		Account account = new Account();
		account.setUsername("jakefrench84@gmail.com");
		account.setPassword(passwordEncoder.encode("password"));
		account.setVerified(true);
		accountRepository.save(account);


		// // define entities
		Company company = new Company();
		company.setName("Natrix Pest Control");
		company.setStreet("12309 Quiet Pasture Dr.");
		company.setCity("Bakersfield");
		company.setState("CA");
		company.setCounty("Kern");
		company.setCountry("United States");
		company.setZip("93312");
		company.setIndustry("Pest Control");
		company.setAccount(account);
		companyRepository.save(company);

		Route route1 = new Route();
		route1.setName("Route 1 - Rosedale");
		route1.setCompany(company);
		routeRepository.save(route1);

		Route route2 = new Route();
		route2.setName("Route 2 - Oildale");
		route2.setCompany(company);
		routeRepository.save(route2);


		// // establish relationships
		// account.setCompany(company);
		// company.setAccount(account);
		

	}

}
