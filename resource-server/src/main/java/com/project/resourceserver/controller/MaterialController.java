package com.project.resourceserver.controller;

import com.project.resourceserver.model.Material;
import com.project.resourceserver.service.MaterialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource/public")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/company/{companyId}/material")
    public ResponseEntity<Material> addNewMaterial(@PathVariable Long companyId, @RequestBody Material material) {
        return materialService.addNewMaterial(companyId, material);
    }
    
}