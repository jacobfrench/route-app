package com.project.resourceserver.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "employee")
public class Employee {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id", length = 36)
  private String id;

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
  @JsonBackReference
  private Company employer;

  @OneToOne(mappedBy = "technician", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Schedule schedule;

  public Employee() {}

  /**
   * @return String return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(final String id) {
    this.id = id;
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
   * @return Schedule return the schedule
   */
  public Schedule getSchedule() {
    return schedule;
  }

  /**
   * @param schedule the schedule to set
   */
  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
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

}