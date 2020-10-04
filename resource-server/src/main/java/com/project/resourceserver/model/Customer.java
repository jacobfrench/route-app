package com.project.resourceserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
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

  @Column(name = "name", length = 80)
  private String lname;

  @Column(name = "primary_phone", length = 10)
  private String primaryPhone;

  @Column(name = "alt_phone", length = 10)
  private String altPhone;

  @Column(name = "email", length = 80)
  private String email;

  @Column(name = "account_id")
  private String accountId;

  enum Status {
    ACTIVE,
    INACTIVE,
    HOLD,
  }

  @Column(name = "status")
  Status status;

  enum ContactPref {
    CALL,
    TEXT,
    TEXT_OR_CALL,
  }

  @Column(name = "primary_phone_pref")
  private ContactPref primePref;

  @Column(name = "alt_phone_pref")
  private ContactPref altPref;

  @OneToMany(mappedBy = "owner")
  private Set<GeoProperty> locations;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id", referencedColumnName = "id")
  @JsonBackReference("company-customers")
  private Company company;

  public Customer() {
    locations = new HashSet<>();
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
  public Set<GeoProperty> getLocations() {
    return locations;
  }

  /**
   * @param ownedProperties the ownedProperties to set
   */
  public void setLocations(Set<GeoProperty> locations) {
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

  public void addLocation(GeoProperty geoProperty) {
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
}
