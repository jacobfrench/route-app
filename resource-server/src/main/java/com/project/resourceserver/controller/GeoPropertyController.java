package com.project.resourceserver.controller;

import java.util.HashSet;
import java.util.Set;

import com.project.resourceserver.model.GeoProperty;
import com.project.resourceserver.model.Tag;
import com.project.resourceserver.repository.GeoPropertyRepository;
import com.project.resourceserver.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource/public")
public class GeoPropertyController {

    @Autowired
    private GeoPropertyRepository geoPropertyRepository;

    @Autowired
    private TagRepository tagRepository;

    public GeoPropertyController(GeoPropertyRepository geoPropertyRepository){
        this.geoPropertyRepository = geoPropertyRepository;
    }

    // GET ****************************************************************************************
    // POST ***************************************************************************************

    @PostMapping(value="/geo_property")
    public ResponseEntity<GeoProperty> addNewGeoProperty(@RequestBody GeoProperty geoProperty) {
        HttpHeaders httpHeaders = new HttpHeaders();

        Set<Tag> tags = new HashSet<>();
        for(Tag tag : geoProperty.getTags()) {
            Tag foundTag = this.tagRepository.findByLabel(tag.getLabel());
            if(foundTag == null) {
                tags.add(tag);
            } else {
                tags.add(foundTag);
            }
        }

        geoProperty.removeTags();

        for(Tag tag : tags){
            geoProperty.addTag(tag);
        }


        geoProperty = this.geoPropertyRepository.save(geoProperty);
        
        if(geoProperty != null)
            return new ResponseEntity<>(geoProperty, httpHeaders, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    // PUT ****************************************************************************************
    // DELETE *************************************************************************************
    
}