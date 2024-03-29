package com.project.resourceserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "unit")
    private String unit;

    @Column(name = "dilution")
    private String dilution;

    @Column(name = "location")
    private String locations;

    @Column(name = "target_pests")
    private String targetPests;

    @Column(name = "note")
    private String note;

    public Material() {
    }

    /**
     * @return double return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return String return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return String return the dilution
     */
    public String getDilution() {
        return dilution;
    }

    /**
     * @param dilution the dilution to set
     */
    public void setDilution(String dilution) {
        this.dilution = dilution;
    }

    /**
     * @return String return the locations
     */
    public String getLocations() {
        return locations;
    }

    /**
     * @param locations the locations to set
     */
    public void setLocations(String locations) {
        this.locations = locations;
    }

    /**
     * @return String return the targetPests
     */
    public String getTargetPests() {
        return targetPests;
    }

    /**
     * @param targetPests the targetPests to set
     */
    public void setTargetPests(String targetPests) {
        this.targetPests = targetPests;
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

    /**
     * @return String return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

}