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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "geo_property")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class GeoProperty {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "phys_street", length = 25)
  private String physStreet;

  @Column(name = "phys_city", length = 17)
  private String physCity;

  @Column(name = "phys_state", length = 2)
  private String physState;

  @Column(name = "phys_country", length = 25)
  private String physCountry;

  @Column(name = "phys_zip", length = 5)
  private String physZip;

  @Column(name = "phys_lat")
  private float lat;

  @Column(name = "phys_lng")
  private float lng;

  @Column(name = "bill_street", length = 25)
  private String billStreet;

  @Column(name = "bill_city", length = 17)
  private String billCity;

  @Column(name = "bill_state", length = 2)
  private String billState;

  @Column(name = "bill_country", length = 25)
  private String billCountry;

  @Column(name = "bill_zip", length = 5)
  private String billZip;

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
   * @return String return the physStreet
   */
  public String getPhysStreet() {
    return physStreet;
  }

  /**
   * @param physStreet the physStreet to set
   */
  public void setPhysStreet(String physStreet) {
    this.physStreet = physStreet;
  }

  /**
   * @return String return the physCity
   */
  public String getPhysCity() {
    return physCity;
  }

  /**
   * @param physCity the physCity to set
   */
  public void setPhysCity(String physCity) {
    this.physCity = physCity;
  }

  /**
   * @return String return the physState
   */
  public String getPhysState() {
    return physState;
  }

  /**
   * @param physState the physState to set
   */
  public void setPhysState(String physState) {
    this.physState = physState;
  }

  /**
   * @return String return the physCountry
   */
  public String getPhysCountry() {
    return physCountry;
  }

  /**
   * @param physCountry the physCountry to set
   */
  public void setPhysCountry(String physCountry) {
    this.physCountry = physCountry;
  }

  /**
   * @return String return the physZip
   */
  public String getPhysZip() {
    return physZip;
  }

  /**
   * @param physZip the physZip to set
   */
  public void setPhysZip(String physZip) {
    this.physZip = physZip;
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
   * @return String return the billStreet
   */
  public String getBillStreet() {
    return billStreet;
  }

  /**
   * @param billStreet the billStreet to set
   */
  public void setBillStreet(String billStreet) {
    this.billStreet = billStreet;
  }

  /**
   * @return String return the billCity
   */
  public String getBillCity() {
    return billCity;
  }

  /**
   * @param billCity the billCity to set
   */
  public void setBillCity(String billCity) {
    this.billCity = billCity;
  }

  /**
   * @return String return the billState
   */
  public String getBillState() {
    return billState;
  }

  /**
   * @param billState the billState to set
   */
  public void setBillState(String billState) {
    this.billState = billState;
  }

  /**
   * @return String return the billCountry
   */
  public String getBillCountry() {
    return billCountry;
  }

  /**
   * @param billCountry the billCountry to set
   */
  public void setBillCountry(String billCountry) {
    this.billCountry = billCountry;
  }

  /**
   * @return String return the billZip
   */
  public String getBillZip() {
    return billZip;
  }

  /**
   * @param billZip the billZip to set
   */
  public void setBillZip(String billZip) {
    this.billZip = billZip;
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

}