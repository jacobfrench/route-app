package com.project.resourceserver.dto;

public class GeoLocationDTO {
  private Long id;
  private String Street;
  private String city;
  private String state;
  private String country;
  private String zip;
  private float lat;
  private float lng;
  private float propertySqFt;
  private float structureSqFt;
  private float linearSqFt;
  private String notes;
  private String mapCode;
  private Long routeId;
  private String routeName;

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
   * @return String return the notes
   */
  public String getNotes() {
    return notes;
  }

  /**
   * @param notes the notes to set
   */
  public void setNotes(String notes) {
    this.notes = notes;
  }

  /**
   * @return String return the mapCode
   */
  public String getMapCode() {
    return mapCode;
  }

  /**
   * @param mapCode the mapCode to set
   */
  public void setMapCode(String mapCode) {
    this.mapCode = mapCode;
  }

  /**
   * @return Long return the routeId
   */
  public Long getRouteId() {
    return routeId;
  }

  /**
   * @param routeId the routeId to set
   */
  public void setRouteId(Long routeId) {
    this.routeId = routeId;
  }

  /**
   * @return String return the routeName
   */
  public String getRouteName() {
    return routeName;
  }

  /**
   * @param routeName the routeName to set
   */
  public void setRouteName(String routeName) {
    this.routeName = routeName;
  }

}