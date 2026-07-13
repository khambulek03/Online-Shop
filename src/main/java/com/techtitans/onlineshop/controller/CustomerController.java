package com.techtitans.onlineshop.controller;

import com.techtitans.onlineshop.domain.Customer;
import com.techtitans.onlineshop.dto.CustomerDataRequest;
import com.techtitans.onlineshop.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "api/products")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String greetings() {
        return "Hello on Railway";
}
