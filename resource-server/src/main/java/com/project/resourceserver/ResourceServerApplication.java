package com.project.resourceserver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.Reader;
import org.springframework.core.io.ClassPathResource;
import org.json.simple.JSONArray;
import java.util.Iterator;

import com.project.resourceserver.model.Account;
import com.project.resourceserver.model.Company;
import com.project.resourceserver.model.Customer;
import com.project.resourceserver.model.GeoProperty;
import com.project.resourceserver.model.Route;
import com.project.resourceserver.repository.AccountRepository;
import com.project.resourceserver.repository.CompanyRepository;
import com.project.resourceserver.repository.CustomerRepository;
import com.project.resourceserver.repository.GeoPropertyRepository;
import com.project.resourceserver.repository.RouteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import org.json.simple.parser.ParseException;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@EnableResourceServer
@SpringBootApplication
public class ResourceServerApplication implements CommandLineRunner{


	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private GeoPropertyRepository geoPropertyRepository;

	@Autowired
  private CustomerRepository customerRepository;
  
  @Autowired
  private ResourceLoader resourceLoader;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		// Account account = new Account();
		// account.setUsername("jakefrench84@gmail.com");
		// account.setPassword(passwordEncoder.encode("password"));
		// account.setVerified(true);
		// accountRepository.save(account);

		

		// Company company = new Company();
		// company.setName("Natrix Pest Control");
		// company.setStreet("12309 Quiet Pasture Dr.");
		// company.setCity("Bakersfield");
		// company.setState("CA");
		// company.setCounty("Kern");
		// company.setCountry("United States");
		// company.setZip("93312");
		// company.setIndustry("Pest Control");
		// company.setAccount(account);
		// companyRepository.save(company);

		// Customer customer = new Customer();
		// customer.setAccountId("123");
		// customer.setFname("John");
		// customer.setMinit("Z");
		// customer.setLname("Doe");
		// customer.setEmail("johndoe@gmail.com");
		// customer.setPrimaryPhone("6615551234");
		// customer.setAltPhone("6613211568");
		// customer.setCompany(company);
		// customerRepository.save(customer);

		// Customer customer2 = new Customer();
		// customer2.setAccountId("A-96582");
		// customer2.setFname("Zark");
		// customer2.setMinit("A");
		// customer2.setLname("Fuckerberg");
		// customer2.setEmail("fuckerberg@gmail.com");
		// customer2.setPrimaryPhone("6152465854");
		// customer2.setAltPhone("9663254125");
		// customer2.setCompany(company);
		// customerRepository.save(customer2);


		// Route route1 = new Route();
		// route1.setName("Route 1 - Rosedale");
		// route1.setCompany(company);
		// routeRepository.save(route1);

		// GeoProperty geoProperty = new GeoProperty();
		// geoProperty.setPhysStreet("6000 Burke Way");
		// geoProperty.setPhysCity("Bakersfield");
		// geoProperty.setPhysState("CA");
		// geoProperty.setPhysCountry("United States");
		// geoProperty.setPhysZip("93309");
		// geoProperty.setLat(35.349540f);
		// geoProperty.setLng(-119.067870f);
		// geoProperty.setRoute(route1);
		// geoProperty.setOwner(customer);
		// geoPropertyRepository.save(geoProperty);

		// GeoProperty geoProperty2 = new GeoProperty();
		// geoProperty2.setPhysStreet("24761 Deertrail Dr");
		// geoProperty2.setPhysCity("Tehachapi");
		// geoProperty2.setPhysState("CA");
		// geoProperty2.setPhysCountry("United States");
		// geoProperty2.setPhysZip("93561");
		// geoProperty2.setLat(35.1788809f);
		// geoProperty2.setLng(-118.649242f);
		// geoProperty2.setRoute(route1);
		// geoProperty2.setOwner(customer);
		// geoPropertyRepository.save(geoProperty2);
		
		// Route route2 = new Route();
		// route2.setName("Route 2 - Oildale");
		// route2.setCompany(company);
    // routeRepository.save(route2);
    populateWithMockData();
  }
  
  private void populateWithMockData() throws IOException, ParseException {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		Account account = new Account();
		account.setUsername("jakefrench84@gmail.com");
		account.setPassword(passwordEncoder.encode("password"));
		account.setVerified(true);
		accountRepository.save(account);

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

    // read json files
    
    // insert customers
    JSONParser parser = new JSONParser();

    Resource resource = new ClassPathResource("mock-customers.json");

	  InputStream input = resource.getInputStream();

    File file = resource.getFile();
    
    try(Reader reader = new FileReader(file)){
      JSONObject jsonObject = (JSONObject) parser.parse(reader);
      JSONArray customers = (JSONArray) jsonObject.get("customers");
      JSONObject line;
      for(int i = 0; i < customers.size(); i++){
        line = (JSONObject)customers.get(i);

        String accountId = Long.toString((Long)line.get("accountId"));
        String fname = (String)line.get("fname");
        String minit = (String)line.get("minit");
        String lname = (String)line.get("lname");
        String email = (String)line.get("email");
        String priPhone = (String)line.get("priPhone");
        String altPhone = (String)line.get("altPhone");

        Customer customer = new Customer();
        customer.setAccountId(accountId);
        customer.setFname(fname);
        customer.setMinit(minit);
        customer.setLname(lname);
        customer.setEmail(email);
        customer.setPrimaryPhone(priPhone);
        customer.setAltPhone(altPhone);
        customer.setCompany(company);
        customerRepository.save(customer);
      }

    } catch (IOException e) {
        e.printStackTrace();
    } catch(ParseException e) {
      e.printStackTrace();
    }
    


 
  }

}
