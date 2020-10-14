package com.project.resourceserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.resourceserver.constants.enums.ContactPref;
import com.project.resourceserver.constants.enums.CustomerStatus;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "fname", length = 80)
  private String fname;

  @Column(name = "minit", length = 1)
  private String minit;

  @Column(name = "lname", length = 80)
  private String lname;

  @Column(name = "primary_phone", length = 10)
  private String primaryPhone;

  @Column(name = "alt_phone", length = 10)
  private String altPhone;

  @Column(name = "email", length = 80)
  private String email;

  @Column(name = "account_id")
  private String accountId;

  @Column(name = "status")
  @Enumerated(EnumType.ORDINAL)
  private CustomerStatus status;

  @Column(name = "primary_phone_pref")
  @Enumerated(EnumType.ORDINAL)
  private ContactPref primePref;

  @Column(name = "alt_phone_pref")
  @Enumerated(EnumType.ORDINAL)
  private ContactPref altPref;

  @Column(name = "billing_street")
  private String billStreet;

  @Column(name = "billing_city")
  private String billCity;

  @Column(name = "billing_state")
  private String billState;

  @Column(name = "billing_county")
  private String billCounty;

  @Column(name = "billing_zip")
  private String billZip;

  @OneToMany(mappedBy = "owner")
  private Set<GeoLocation> locations;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id", referencedColumnName = "id")
  @JsonBackReference("company-customers")
  private Company company;

  public Customer() {
    locations = new HashSet<>();
    setPrimePref(ContactPref.NONE);
    setAltPref(ContactPref.NONE);

  }

  /**
   * @return String return the fname
   */
  public String getFname() {
    return fname;
  }

  /**
   * @param fname the fname to set
   */
  public void setFname(String fname) {
    this.fname = fname;
  }

  /**
   * @return String return the lname
   */
  public String getLname() {
    return lname;
  }

  /**
   * @param lname the lname to set
   */
  public void setLname(String lname) {
    this.lname = lname;
  }

  /**
   * @return String return the primaryPhone
   */
  public String getPrimaryPhone() {
    return primaryPhone;
  }

  /**
   * @param primaryPhone the primaryPhone to set
   */
  public void setPrimaryPhone(String primaryPhone) {
    this.primaryPhone = primaryPhone;
  }

  /**
   * @return String return the altPhone
   */
  public String getAltPhone() {
    return altPhone;
  }

  /**
   * @param altPhone the altPhone to set
   */
  public void setAltPhone(String altPhone) {
    this.altPhone = altPhone;
  }

  /**
   * @return ContactPref return the primePref
   */
  public ContactPref getPrimePref() {
    return primePref;
  }

  /**
   * @param primePref the primePref to set
   */
  public void setPrimePref(ContactPref primePref) {
    this.primePref = primePref;
  }

  /**
   * @return ContactPref return the altPref
   */
  public ContactPref getAltPref() {
    return altPref;
  }

  /**
   * @param altPref the altPref to set
   */
  public void setAltPref(ContactPref altPref) {
    this.altPref = altPref;
  }

  /**
   * @return Set<GeoProperty> return the ownedProperties
   */
  public Set<GeoLocation> getLocations() {
    return locations;
  }

  /**
   * @param ownedProperties the ownedProperties to set
   */
  public void setLocations(Set<GeoLocation> locations) {
    this.locations = locations;
  }

  /**
   * @return Company return the company
   */
  public Company getCompany() {
    return company;
  }

  /**
   * @param company the company to set
   */
  public void setCompany(Company company) {
    this.company = company;
  }

  public void addLocation(GeoLocation geoProperty) {
    this.locations.add(geoProperty);
  }

  /**
   * @return Long return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return String return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return String return the accountId
   */
  public String getAccountId() {
    return accountId;
  }

  /**
   * @param accountId the accountId to set
   */
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  /**
   * @return String return the minit
   */
  public String getMinit() {
    return minit;
  }

  /**
   * @param minit the minit to set
   */
  public void setMinit(String minit) {
    this.minit = minit;
  }

  /**
   * @return CustomerStatus return the status
   */
  public CustomerStatus getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(CustomerStatus status) {
    this.status = status;
  }

  /**
   * @return String return the billStreet
   */
  public String getBillStreet() {
    return billStreet;
  }

  /**
   * @param billStreet the billStreet to set
   */
  public void setBillStreet(String billStreet) {
    this.billStreet = billStreet;
  }

  /**
   * @return String return the billCity
   */
  public String getBillCity() {
    return billCity;
  }

  /**
   * @param billCity the billCity to set
   */
  public void setBillCity(String billCity) {
    this.billCity = billCity;
  }

  /**
   * @return String return the billState
   */
  public String getBillState() {
    return billState;
  }

  /**
   * @param billState the billState to set
   */
  public void setBillState(String billState) {
    this.billState = billState;
  }

  /**
   * @return String return the billCounty
   */
  public String getBillCounty() {
    return billCounty;
  }

  /**
   * @param billCounty the billCounty to set
   */
  public void setBillCounty(String billCounty) {
    this.billCounty = billCounty;
  }

  /**
   * @return String return the billZip
   */
  public String getBillZip() {
    return billZip;
  }

  /**
   * @param billZip the billZip to set
   */
  public void setBillZip(String billZip) {
    this.billZip = billZip;
  }
}
