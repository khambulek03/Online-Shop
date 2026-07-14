package com.techtitans.onlineshop.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "api/products")
public class CustomerController {

    @GetMapping("/")
    public String greetings() {
        return "Hello on Railway";
    }
}
