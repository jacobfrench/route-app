package com.project.resourceserver;

import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.Reader;
import org.springframework.core.io.ClassPathResource;

import com.project.resourceserver.model.Account;
import com.project.resourceserver.model.Company;
import com.project.resourceserver.model.Customer;
import com.project.resourceserver.model.GeoLocation;
import com.project.resourceserver.model.Route;
import com.project.resourceserver.repository.AccountRepository;
import com.project.resourceserver.repository.CompanyRepository;
import com.project.resourceserver.repository.CustomerRepository;
import com.project.resourceserver.repository.GeoLocationRepository;
import com.project.resourceserver.repository.RouteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import org.json.simple.parser.JSONParser;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


import org.springframework.core.io.Resource;

@EnableResourceServer
@SpringBootApplication
public class ResourceServerApplication implements CommandLineRunner {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private GeoLocationRepository geoPropertyRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
  }
  
  @Bean
  public Mapper dozerBeanMapper() {
  //  return DozerBeanMapperBuilder.buildDefault();
    return new DozerBeanMapper();
}

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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

		Route route1 = new Route();
		route1.setName("Route 1 - Kern County");
		route1.setCompany(company);
		routeRepository.save(route1);

		// insert customers
		JSONParser parser = new JSONParser();

		Resource customersResource = new ClassPathResource("mock-customers.json");
		Resource addressesResource = new ClassPathResource("mock-addresses.json");

		File customersFile = customersResource.getFile();
		File addressesFile = addressesResource.getFile();

		// insert customers and geoproperties
		try {
			Reader reader = new FileReader(customersFile);
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			JSONArray customers = (JSONArray) jsonObject.get("customers");

			reader = new FileReader(addressesFile);
			jsonObject = (JSONObject) parser.parse(reader);
			JSONArray addresses = (JSONArray) jsonObject.get("addresses");

			reader.close();

			JSONObject line;
			for (int i = 0; i < customers.size(); i++) {
				if (i >= addresses.size()-1)
					break;

				line = (JSONObject) customers.get(i);

				String accountId = Long.toString((Long) line.get("accountId"));
				String fname = (String) line.get("fname");
				String minit = (String) line.get("minit");
				String lname = (String) line.get("lname");
				String email = (String) line.get("email");
				String priPhone = (String) line.get("priPhone");
				String altPhone = (String) line.get("altPhone");

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

				line = (JSONObject) addresses.get(i);

				String street = (String) line.get("street");
				String city = (String) line.get("city");
				String state = (String) line.get("state");
				String zip = (String) line.get("zip");
				float lat = Float.parseFloat((String)line.get("lat"));
				float lng = Float.parseFloat((String)line.get("lng"));

				GeoLocation geoProperty = new GeoLocation();
				geoProperty.setStreet(street);
				geoProperty.setCity(city);
				geoProperty.setState(state);
				geoProperty.setCountry("United States");
				geoProperty.setZip(zip);
				geoProperty.setLat(lat);
				geoProperty.setLng(lng);
				geoProperty.setRoute(route1);
				geoProperty.setOwner(customer);
				geoProperty = geoPropertyRepository.save(geoProperty);

				company.addGeoProperty(geoProperty);


			}
			companyRepository.save(company);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
