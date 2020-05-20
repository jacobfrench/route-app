package com.project.resourceserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "primary_phone")
    private String primaryPhone;

    @Column(name = "secondary_phone")
    private String secondaryPhone;

    @OneToMany(mappedBy = "owner")
    private Set<GeoProperty> ownedProperties;

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
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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

}