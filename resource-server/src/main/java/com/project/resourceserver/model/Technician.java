package com.project.resourceserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Technician extends Employee{

    @Column(name="route")
    private String route;

    
    

    /**
     * @return String return the route
     */
    public String getRoute() {
        return route;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(String route) {
        this.route = route;
    }

}