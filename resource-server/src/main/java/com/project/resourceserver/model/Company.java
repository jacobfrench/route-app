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

  @Column(name = "zip")
  private String zip;

  @Column(name = "industry")
  private String industry;

  @OneToOne(fetch = FetchType.LAZY)
  @JsonBackReference
  private Account account;

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference
  private Set<Route> routes;

  @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference
  private Set<Employee> employees;

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

}