package com.project.resourceserver.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Technician extends Employee {

    public Technician() {}

    @Column(name = "license_num")
    private String licenseNum;

    @OneToMany
    private List<Route> routes;

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

}