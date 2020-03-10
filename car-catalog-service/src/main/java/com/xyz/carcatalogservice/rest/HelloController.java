package com.xyz.carcatalogservice.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from " + applicationName;
    }
}
