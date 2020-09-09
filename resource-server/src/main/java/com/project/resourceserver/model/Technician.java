package com.project.resourceserver.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Technician extends Employee {

    public Technician() {}

    @Column(name = "license_num")
    private String licenseNum;

    @OneToMany
    private List<Route> routes;

    @OneToOne(mappedBy = "technician", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference("technician-schedule")
    private Schedule schedule;

    /**
     * @return List<Route> return the routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * @param routes the routes to set
     */
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    /**
     * @return String return the licenseNum
     */
    public String getLicenseNum() {
        return licenseNum;
    }

    /**
     * @param licenseNum the licenseNum to set
     */
    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
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

}