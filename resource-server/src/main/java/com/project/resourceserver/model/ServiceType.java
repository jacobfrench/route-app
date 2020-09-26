package com.project.resourceserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service_type")
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost", length = 10)
    private String cost;

    @Column(name = "applied_taxes", length = 5)
    private String appliedTaxes;

    @Column(name = "always_confirm")
    private boolean alwaysConfirm;

    @Column(name = "color_code", length = 7)
    private String colorCodeHex;

    // Note: this enum is of type ORDINAL. If any entries need to be added at a
    // later date,
    // append them to the end of the current enum. !!! DO NOT reorder this enum once
    // in production !!!
    public enum Repeat {
        NONE, DAILY, WEEKLY, MONTHLY, BIMONTHLY, QUARTERLY, YEARLY
    };

    @Enumerated(EnumType.ORDINAL)
    private Repeat repeat;

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String return the cost
     */
    public String getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

    /**
     * @return String return the appliedTaxes
     */
    public String getAppliedTaxes() {
        return appliedTaxes;
    }

    /**
     * @param appliedTaxes the appliedTaxes to set
     */
    public void setAppliedTaxes(String appliedTaxes) {
        this.appliedTaxes = appliedTaxes;
    }

    /**
     * @return boolean return the alwaysConfirm
     */
    public boolean isAlwaysConfirm() {
        return alwaysConfirm;
    }

    /**
     * @param alwaysConfirm the alwaysConfirm to set
     */
    public void setAlwaysConfirm(boolean alwaysConfirm) {
        this.alwaysConfirm = alwaysConfirm;
    }

    /**
     * @return String return the colorCodeHex
     */
    public String getColorCodeHex() {
        return colorCodeHex;
    }

    /**
     * @param colorCodeHex the colorCodeHex to set
     */
    public void setColorCodeHex(String colorCodeHex) {
        this.colorCodeHex = colorCodeHex;
    }

    /**
     * @return Repeat return the repeat
     */
    public Repeat getRepeat() {
        return repeat;
    }

    /**
     * @param repeat the repeat to set
     */
    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
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