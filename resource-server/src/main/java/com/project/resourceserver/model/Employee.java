package com.project.resourceserver.model;

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

  @Column(name = "name")
  private String name;

  @Column(name = "title")
  private String title;

  @Column(name = "authorized")
  private boolean authorized;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "emoployer_id", referencedColumnName = "id")
  @JsonBackReference
  private Company employer;

  @OneToOne(mappedBy = "technician", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Schedule schedule;

  public Employee() {

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
   * @return String return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(final String name) {
    this.name = name;
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

}