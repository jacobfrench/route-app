package com.project.resourceserver.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "schedule")
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch =
    // FetchType.LAZY)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Job> jobs;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference("technician-schedule")
    private Technician technician;

    public Schedule() {
        jobs = new HashSet<>();
    }

    public void addJob(Job job) {
        this.jobs.add(job);
    }

    /**
     * @return Set<Job> return the jobs
     */
    public Set<Job> getJobs() {
        return jobs;
    }

    /**
     * @param jobs the jobs to set
     */
    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    /**
     * @return Technician return the technician
     */
    public Technician getTechnician() {
        return technician;
    }

    /**
     * @param technician the technician to set
     */
    public void setTechnician(Technician technician) {
        this.technician = technician;
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