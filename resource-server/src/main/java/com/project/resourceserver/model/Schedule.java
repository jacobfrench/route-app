package com.project.resourceserver.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Job> currentJobs;

    @OneToOne(fetch = FetchType.LAZY)
    private Technician technician;

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
     * @return Set<Job> return the currentJobs
     */
    public Set<Job> getCurrentJobs() {
        return currentJobs;
    }

    /**
     * @param currentJobs the currentJobs to set
     */
    public void setCurrentJobs(Set<Job> currentJobs) {
        this.currentJobs = currentJobs;
    }

}