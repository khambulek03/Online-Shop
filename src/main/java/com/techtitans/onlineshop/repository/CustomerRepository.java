package com.techtitans.onlineshop.repository;

import com.techtitans.onlineshop.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Page<Customer> findAll(Pageable pageable);

    Optional<Customer> findCustomerByEmail(String email);


}
