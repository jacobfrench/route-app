package com.project.resourceserver.controller;

import com.project.resourceserver.model.GeoLocation;
import com.project.resourceserver.service.GeoLocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource/public")
public class GeoLocationController {

    @Autowired
    private GeoLocationService geoPropertyService;

    public GeoLocationController(GeoLocationService geoPropertyService){
        this.geoPropertyService = geoPropertyService;
    }

    // GET ****************************************************************************************
    // POST ***************************************************************************************

    @PostMapping(value="/company/{companyId}/customer/{customerId}/service_type/{serviceTypeId}/geo_property")
    public ResponseEntity<GeoLocation> addNewGeoProperty(@PathVariable Long companyId, @PathVariable Long customerId, 
                                                         @PathVariable Long serviceTypeId, @RequestBody GeoLocation geoProperty) {
        return geoPropertyService.addNewGeoProperty(companyId, customerId, serviceTypeId,  geoProperty);
    }

    // PUT ****************************************************************************************
    // DELETE *************************************************************************************
    
}