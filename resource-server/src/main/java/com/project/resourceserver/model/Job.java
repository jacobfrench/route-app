package com.project.resourceserver.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "job")
@JsonIgnoreProperties(value={"hibernateLazyInitializer", "$$_hibernate_interceptor"})
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

    // Note: this enum is of type ORDINAL. If any entries need to be added at a later date,
    // append them to the end of the current enum. !!! DO NOT reorder this enum once in production !!!
    public enum Status {CREATED, ACTIVE, COMPLETED, CANCELED};

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private GeoProperty property;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    // private Schedule schedule;

    @ManyToMany
    Set<Tag> tags;

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

    /**
     * @return Status return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    // public void setSchedule(Schedule schedule) {
    //     this.schedule = schedule;
    // }


}