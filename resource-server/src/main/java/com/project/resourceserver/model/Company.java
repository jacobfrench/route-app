package com.project.resourceserver.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Company {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id", length = 36)
  private String id;

  @Column(name = "name")
  private String name;

  @Column(name = "street")
  private String street;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "county")
  private String county;

  @Column(name = "country")
  private String country;

  @Column(name = "zip", length = 5)
  private String zip;

  @Column(name = "industry")
  private String industry;

  @Column(name = "website")
  private String website;

  @Column(name = "timezone")
  private String timezone;

  @Column(name = "currency")
  private String currency;

  @Column(name = "businessLicense")
  private String businessLicense;

  @Column(name = "operatingHoursOpen")
  private String operatingHoursOpen;

  @Column(name = "operatingHoursClose")
  private String operatingHoursClose;

  @OneToOne(fetch = FetchType.LAZY)
  @JsonBackReference("account-company")
  private Account account;

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference("company-routes")
  private Set<Route> routes;

  @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference("company-employer")
  private Set<Employee> employees;

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference("company-customers")
  private Set<Customer> customers;

  @OneToMany
  private Set<GeoProperty> geoProperties;

  public Company() {
    routes = new HashSet<>();
    employees = new HashSet<>();
  }

  /**
   * @return String return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return String return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return String return the street
   */
  public String getStreet() {
    return street;
  }

  /**
   * @param street the street to set
   */
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   * @return String return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return String return the state
   */
  public String getState() {
    return state;
  }

  /**
   * @param state the state to set
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return String return the country
   */
  public String getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * @return String return the zip
   */
  public String getZip() {
    return zip;
  }

  /**
   * @param zip the zip to set
   */
  public void setZip(String zip) {
    this.zip = zip;
  }

  /**
   * @return String return the industry
   */
  public String getIndustry() {
    return industry;
  }

  /**
   * @param industry the industry to set
   */
  public void setIndustry(String industry) {
    this.industry = industry;
  }

  /**
   * @return Set<Route> return the routes
   */
  public Set<Route> getRoutes() {
    return routes;
  }

  /**
   * @param routes the routes to set
   */
  public void setRoutes(Set<Route> routes) {
    this.routes = routes;
  }

  /**
   * @return Set<Employee> return the employees
   */
  public Set<Employee> getEmployees() {
    return employees;
  }

  /**
   * @param employees the employees to set
   */
  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  /**
   * @return Account return the account
   */
  public Account getAccount() {
    return account;
  }

  /**
   * @param account the account to set
   */
  public void setAccount(Account account) {
    this.account = account;
  }

  /**
   * @return String return the county
   */
  public String getCounty() {
    return county;
  }

  /**
   * @param county the county to set
   */
  public void setCounty(String county) {
    this.county = county;
  }

  /**
   * @return Set<Customer> return the customers
   */
  public Set<Customer> getCustomers() {
    return customers;
  }

  /**
   * @param customers the customers to set
   */
  public void setCustomers(Set<Customer> customers) {
    this.customers = customers;
  }

  /**
   * @return Set<GeoProperty> return the geoProperties
   */
  public Set<GeoProperty> getGeoProperties() {
    return geoProperties;
  }

  /**
   * @param geoProperties the geoProperties to set
   */
  public void setGeoProperties(Set<GeoProperty> geoProperties) {
    this.geoProperties = geoProperties;
  }

  public void addGeoProperty(GeoProperty geoProperty) {
    this.geoProperties.add(geoProperty);
  }

  public void addCustomer(Customer customer) {
    this.customers.add(customer);
  }

  /**
   * @return String return the website
   */
  public String getWebsite() {
    return website;
  }

  /**
   * @param website the website to set
   */
  public void setWebsite(String website) {
    this.website = website;
  }

  /**
   * @return String return the timezone
   */
  public String getTimezone() {
    return timezone;
  }

  /**
   * @param timezone the timezone to set
   */
  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  /**
   * @return String return the currency
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * @param currency the currency to set
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * @return String return the businessLicense
   */
  public String getBusinessLicense() {
    return businessLicense;
  }

  /**
   * @param businessLicense the businessLicense to set
   */
  public void setBusinessLicense(String businessLicense) {
    this.businessLicense = businessLicense;
  }

  /**
   * @return String return the operatingHoursOpen
   */
  public String getOperatingHoursOpen() {
    return operatingHoursOpen;
  }

  /**
   * @param operatingHoursOpen the operatingHoursOpen to set
   */
  public void setOperatingHoursOpen(String operatingHoursOpen) {
    this.operatingHoursOpen = operatingHoursOpen;
  }

  /**
   * @return String return the operatingHoursClose
   */
  public String getOperatingHoursClose() {
    return operatingHoursClose;
  }

  /**
   * @param operatingHoursClose the operatingHoursClose to set
   */
  public void setOperatingHoursClose(String operatingHoursClose) {
    this.operatingHoursClose = operatingHoursClose;
  }

}