package com.project.resourceserver.controller;

import com.project.resourceserver.model.GeoProperty;
import com.project.resourceserver.service.GeoPropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource/public")
public class GeoPropertyController {

    @Autowired
    private GeoPropertyService geoPropertyService;

    public GeoPropertyController(GeoPropertyService geoPropertyService){
        this.geoPropertyService = geoPropertyService;
    }

    // GET ****************************************************************************************
    // POST ***************************************************************************************

    @PostMapping(value="/company/{companyId}/customer/{customerId}/service_type/{serviceTypeId}/geo_property")
    public ResponseEntity<GeoProperty> addNewGeoProperty(@PathVariable Long companyId, @PathVariable Long customerId, 
                                                         @PathVariable Long serviceTypeId, @RequestBody GeoProperty geoProperty) {
        return geoPropertyService.addNewGeoProperty(companyId, customerId, serviceTypeId,  geoProperty);
    }

    // PUT ****************************************************************************************
    // DELETE *************************************************************************************
    
}