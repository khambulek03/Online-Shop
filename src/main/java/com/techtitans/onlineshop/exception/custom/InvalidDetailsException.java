package com.techtitans.onlineshop.exception.custom;

/*
* Exception thrown if the details entered are in an incorrect format
* e.g, Integer age = 20.5; => exception thrown
* e.g, Double amount = "30"; => exception thrown
 * */

public class InvalidDetailsException extends RuntimeException {
    public InvalidDetailsException(String message) {
        super(message);
    }
}
