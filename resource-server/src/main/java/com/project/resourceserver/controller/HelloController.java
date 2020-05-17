package com.project.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class HelloController {
    
    @GetMapping(value = "/public")
    public String publicHello() {
        return "Hello, World!";
    }

    @GetMapping(value = "/private")
    public String privateHello() {
        return "Hello, World! (private)";
    }
}