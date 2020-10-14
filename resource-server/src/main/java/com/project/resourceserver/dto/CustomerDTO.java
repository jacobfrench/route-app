package com.project.resourceserver.dto;

import com.project.resourceserver.constants.enums.ContactPref;
import com.project.resourceserver.constants.enums.CustomerStatus;

public class CustomerDTO {

  private Long id;

  private String fname;

  private String minit;

  private String lname;

  private String primaryPhone;

  private String altPhone;

  private String email;

  private String accountId;

  private CustomerStatus status;

  private ContactPref primePref;

  private ContactPref altPref;

  private String billStreet;

  private String billCity;

  private String billState;

  private String billCounty;

  private String billZip;

  public CustomerDTO() {
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