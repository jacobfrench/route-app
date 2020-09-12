package com.project.resourceserver.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Customer {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "fname")
    private String fname;

    @Column(name = "name")
    private String lname;

    @Column(name = "primary_phone")
    private String primaryPhone;

    @Column(name = "secondary_phone")
    private String secondaryPhone;

    @OneToMany(mappedBy = "owner")
    private Set<GeoProperty> ownedProperties;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @JsonBackReference("company-customers")
    private Company company;

    public Customer() {
        ownedProperties = new HashSet<>();
    }

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
     * @return String return the fname
     */
    public String getFirstName() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFirstName(String fname) {
        this.fname = fname;
    }

    /**
     * @return String return the lname
     */
    public String getLastName() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLastName(String lname) {
        this.lname = lname;
    }

    /**
     * @return String return the primaryPhone
     */
    public String getPrimaryPhone() {
        return primaryPhone;
    }

    /**
     * @param primaryPhone the primaryPhone to set
     */
    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    /**
     * @return String return the secondaryPhone
     */
    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    /**
     * @param secondaryPhone the secondaryPhone to set
     */
    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    /**
     * @return Set<GeoProperty> return the ownedProperties
     */
    public Set<GeoProperty> getOwnedProperties() {
        return ownedProperties;
    }

    /**
     * @param ownedProperties the ownedProperties to set
     */
    public void setOwnedProperties(Set<GeoProperty> ownedProperties) {
        this.ownedProperties = ownedProperties;
    }

    /**
     * @return Company return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

}