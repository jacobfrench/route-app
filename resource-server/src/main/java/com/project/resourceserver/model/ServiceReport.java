package com.project.resourceserver.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "service_report")
public class ServiceReport {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id", length = 36)
  private String id;

  @Column(name = "scheduled_date_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime completedDateTime;

  public ServiceReport(){}

  /**
   * @return LocalDateTime return the completedDateTime
   */
  public LocalDateTime getCompletedDateTime() {
    return completedDateTime;
  }

  /**
   * @param completedDateTime the completedDateTime to set
   */
  public void setCompletedDateTime(LocalDateTime completedDateTime) {
    this.completedDateTime = completedDateTime;
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

}