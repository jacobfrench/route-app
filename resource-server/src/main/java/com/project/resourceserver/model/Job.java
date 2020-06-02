package com.project.resourceserver.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Job {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "scheduled_date_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime scheduledDateTime;

    @Column(name = "time_completed")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime timeCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private GeoProperty property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;

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
     * @return GeoProperty return the property
     */
    public GeoProperty getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(GeoProperty property) {
        this.property = property;
    }

    /**
     * @return LocalDateTime return the scheduledDateTime
     */
    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }

    /**
     * @param scheduledDateTime the scheduledDateTime to set
     */
    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }


    /**
     * @return LocalDateTime return the timeCompleted
     */
    public LocalDateTime getTimeCompleted() {
        return timeCompleted;
    }

    /**
     * @param timeCompleted the timeCompleted to set
     */
    public void setTimeCompleted(LocalDateTime timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

}