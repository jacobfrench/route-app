package com.project.resourceserver.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "employee")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "employee_id")
  private String employeeId;

  @Column(name = "fname")
  private String fname;

  @Column(name = "lname")
  private String lname;

  @Column(name = "title")
  private String title;

  @Column(name = "authorized")
  private boolean authorized;

  @Column(name = "password")
  private String password;

  @Column(name = "hire_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime hireDate;

  @Column(name = "last_login")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime lastLogin;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "emoployer_id", referencedColumnName = "id")
  @JsonBackReference("company-employer")
  private Company employer;

  public Employee() {
  }

  /**
   * @return String return the employeeId
   */
  public String getEmployeeId() {
    return employeeId;
  }

  /**
   * @param employeeId the employeeId to set
   */
  public void setEmployeeId(final String employeeId) {
    this.employeeId = employeeId;
  }

  /**
   * @return String return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(final String title) {
    this.title = title;
  }

  /**
   * @return Company return the employer
   */
  public Company getEmployer() {
    return employer;
  }

  /**
   * @param employer the employer to set
   */
  public void setEmployer(Company employer) {
    this.employer = employer;
  }

  /**
   * @return boolean return the authorized
   */
  public boolean isAuthorized() {
    return authorized;
  }

  /**
   * @param authorized the authorized to set
   */
  public void setAuthorized(boolean authorized) {
    this.authorized = authorized;
  }

  /**
   * @return LocalDateTime return the hireDate
   */
  public LocalDateTime getHireDate() {
    return hireDate;
  }

  /**
   * @param hireDate the hireDate to set
   */
  public void setHireDate(LocalDateTime hireDate) {
    this.hireDate = hireDate;
  }

  /**
   * @return LocalDateTime return the lastLogin
   */
  public LocalDateTime getLastLogin() {
    return lastLogin;
  }

  /**
   * @param lastLogin the lastLogin to set
   */
  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

  /**
   * @return String return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
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

}