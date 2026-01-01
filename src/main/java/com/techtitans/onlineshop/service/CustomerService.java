package com.techtitans.onlineshop.service;

import com.techtitans.onlineshop.domain.Customer;
import com.techtitans.onlineshop.dto.CustomerDataRequest;
import com.techtitans.onlineshop.exception.custom.NotFoundException;
import com.techtitans.onlineshop.exception.custom.ObjectAlreadyExistException;
import com.techtitans.onlineshop.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<Customer> customers(int page, int size, String sort) {
        /*
        * if repository contains over 1000 customers, then return x pages (where x = page size)
        * if size = 20, then 20 customers per page
        * sort is sorting them within a page, while sorting is database dedicated
        * */
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        return customerRepository.findAll(pageable);
    }

    public Customer customer(String email) {
        /*
        * returns a customer from repository
        * if not exists then throw an exception
        **/
        return customerRepository.findCustomerByEmail(email)
                .orElseThrow(
                        () -> new NotFoundException("Customer email: " + email + " was not found!")
                );
    }

    public boolean customer(UUID customerId) {
        return customerRepository.existsById(customerId);
    }

    public Customer register(CustomerDataRequest customerData) {
        String email = customerData.email();
        // first check if the email is taken or not
        boolean existsByEmail = customerRepository.existsByEmail(email);
        if (existsByEmail) {
            logger.info("Email {} is already taken!", email);
            throw new ObjectAlreadyExistException("Email: " + email + " already taken!");
        }

        String phone = customerData.phone();
        // then check if the phone number is taken or not
        boolean existsByPhone = customerRepository.existsByPhone(phone);
        if (existsByPhone) {
            logger.info("Phone {} is already taken!", phone);
            throw new ObjectAlreadyExistException("Phone: " + phone + " already taken!");
        }

        // finalize customer creation
        Customer customer = new Customer();
        customer.setName(customerData.name());
        customer.setSurname(customerData.surname());
        customer.setEmail(email);
        customer.setPhone(customerData.phone());

        logger.info("Customer object is finally created {}", customer);
        return customerRepository.save(customer);
    }
}
