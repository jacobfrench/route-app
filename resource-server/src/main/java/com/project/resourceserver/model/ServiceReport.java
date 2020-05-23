package com.project.resourceserver.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "service_report")
public class ServiceReport {

    @Column(name = "scheduled_date_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime completedDateTime;

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

}