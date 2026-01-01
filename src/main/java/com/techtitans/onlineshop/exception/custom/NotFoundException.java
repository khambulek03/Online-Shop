package com.techtitans.onlineshop.exception.custom;

/*
* Exception thrown when searching from the database
* GET /product_id => thrown if the product_id does not exist
* */

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
