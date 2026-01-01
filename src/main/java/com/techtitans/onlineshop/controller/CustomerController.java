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
@RequestMapping(path = "api/shop/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    @ResponseStatus(OK)
    public Page<Customer> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "20") int size,
                                          @RequestParam(defaultValue = "name") String sort) {
        return customerService.customers(page, size, sort);
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@Valid @RequestBody CustomerDataRequest dataRequest) {
        Customer customer = customerService.register(dataRequest);
        return customer != null ? ResponseEntity.status(CREATED).body(customer) : ResponseEntity.badRequest().build();
    }
}
