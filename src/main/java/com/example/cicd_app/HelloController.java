package com.example.cicd_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "<h1>Hello from CI/CD Pipeline - Version 1</h1>";
    }
}
