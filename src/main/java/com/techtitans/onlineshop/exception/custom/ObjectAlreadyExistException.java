package com.techtitans.onlineshop.exception.custom;

/*
* Exception thrown if the item already exists in database
* e.g, Inserting a new customer with the other customers email, phone, id_no.
*       or any other unique field.
* */

public class ObjectAlreadyExistException extends RuntimeException {
    public ObjectAlreadyExistException(String message) {
        super(message);
    }
}
