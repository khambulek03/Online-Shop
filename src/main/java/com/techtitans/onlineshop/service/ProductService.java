package com.techtitans.onlineshop.service;

import com.techtitans.onlineshop.domain.Product;
import com.techtitans.onlineshop.dto.ProductDataRequest;
import com.techtitans.onlineshop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> products(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        return productRepository.findAll(pageable);
    }

    public Page<Product> product(UUID id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return productRepository.findAllById(id, pageable);
    }

    public Page<Product> product(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return productRepository.findAllByName(name ,pageable);
    }

    public Product createProduct(ProductDataRequest productDataRequest) {
        var product = create(productDataRequest);
        logger.info("Product created successfully @{}", LocalTime.now(Clock.systemUTC()));
        return productRepository.save(product);
    }

    private Product create(ProductDataRequest productDataRequest) {
        Product product = new Product();
        product.setName(productDataRequest.name());
        product.setModel(productDataRequest.model());
        product.setRam(productDataRequest.ram());
        product.setRom(productDataRequest.rom());
        product.setCpu(productDataRequest.cpu());
        product.setQuantity(productDataRequest.quantity());
        logger.info("Problem encountered while creating product {}", product);
        return product;
    }
}
