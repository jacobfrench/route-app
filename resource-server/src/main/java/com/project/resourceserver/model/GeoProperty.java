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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "geo_property")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class GeoProperty {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "street", length = 80)
  private String Street;

  @Column(name = "city", length = 17)
  private String city;

  @Column(name = "state", length = 2)
  private String state;

  @Column(name = "country", length = 25)
  private String country;

  @Column(name = "zip", length = 10)
  private String zip;

  @Column(name = "lat")
  private float lat;

  @Column(name = "lng")
  private float lng;

  @Column(name = "property_sq_ft")
  private float propertySqFt;

  @Column(name = "structure_sq_ft")
  private float structureSqFt;

  @Column(name = "linear_sq_ft")
  private float linearSqFt;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  @JsonBackReference("geo_property-owner")
  private Customer owner;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "route_id", referencedColumnName = "id")
  @JsonBackReference("geo_property-route")
  private Route route;

  @ManyToMany(cascade = CascadeType.ALL)
  Set<Tag> tags;

  @OneToOne(fetch = FetchType.LAZY)
  private ServiceType serviceType;

  public GeoProperty() {
    tags = new HashSet<>();
  }

  public void removeTags() {
    this.tags = new HashSet<>();
  }

  public void setTags(Set<Tag> tags) {
    this.tags = tags;
  }

  public Set<Tag> getTags() {
    return this.tags;
  }

  /**
   * @return String return the Street
   */
  public String getStreet() {
    return Street;
  }

  /**
   * @param Street the Street to set
   */
  public void setStreet(String Street) {
    this.Street = Street;
  }

  /**
   * @return float return the lat
   */
  public float getLat() {
    return lat;
  }

  /**
   * @param lat the lat to set
   */
  public void setLat(float lat) {
    this.lat = lat;
  }

  /**
   * @return float return the lng
   */
  public float getLng() {
    return lng;
  }

  /**
   * @param lng the lng to set
   */
  public void setLng(float lng) {
    this.lng = lng;
  }

  /**
   * @return float return the propertySqFt
   */
  public float getPropertySqFt() {
    return propertySqFt;
  }

  /**
   * @param propertySqFt the propertySqFt to set
   */
  public void setPropertySqFt(float propertySqFt) {
    this.propertySqFt = propertySqFt;
  }

  /**
   * @return float return the structureSqFt
   */
  public float getStructureSqFt() {
    return structureSqFt;
  }

  /**
   * @param structureSqFt the structureSqFt to set
   */
  public void setStructureSqFt(float structureSqFt) {
    this.structureSqFt = structureSqFt;
  }

  /**
   * @return float return the linearSqFt
   */
  public float getLinearSqFt() {
    return linearSqFt;
  }

  /**
   * @param linearSqFt the linearSqFt to set
   */
  public void setLinearSqFt(float linearSqFt) {
    this.linearSqFt = linearSqFt;
  }

  /**
   * @return Customer return the owner
   */
  public Customer getOwner() {
    return owner;
  }

  /**
   * @param owner the owner to set
   */
  public void setOwner(Customer owner) {
    this.owner = owner;
  }

  /**
   * @return Route return the route
   */
  public Route getRoute() {
    return route;
  }

  /**
   * @param route the route to set
   */
  public void setRoute(Route route) {
    this.route = route;
  }

  /**
   * @return ServiceType return the serviceType
   */
  public ServiceType getServiceType() {
    return serviceType;
  }

  /**
   * @param serviceType the serviceType to set
   */
  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
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
   * @return String return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return String return the state
   */
  public String getState() {
    return state;
  }

  /**
   * @param state the state to set
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return String return the country
   */
  public String getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * @return String return the zip
   */
  public String getZip() {
    return zip;
  }

  /**
   * @param zip the zip to set
   */
  public void setZip(String zip) {
    this.zip = zip;
  }

}