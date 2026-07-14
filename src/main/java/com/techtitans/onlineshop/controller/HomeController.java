package com.techtitans.onlineshop.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
            "application", "Online Shop API",
            "status", "Running",
            "products", "/api/products"
        );
    }
}