package com.techtitans.onlineshop.exception;

import com.techtitans.onlineshop.exception.custom.InvalidDetailsException;
import com.techtitans.onlineshop.exception.custom.NotFoundException;
import com.techtitans.onlineshop.exception.custom.ObjectAlreadyExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectAlreadyExistException.class)
    public ResponseEntity<Object> duplicateFieldException(ObjectAlreadyExistException duplicateFieldException) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", duplicateFieldException.getMessage());
        response.put("status", CONFLICT.value());

        return new ResponseEntity<>(response, CONFLICT);
    }

    @ExceptionHandler(InvalidDetailsException.class)
    public ResponseEntity<Object> invalidDetails(InvalidDetailsException invalidDetailsException) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", invalidDetailsException.getMessage());
        response.put("status", BAD_REQUEST.value());

        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(NotFoundException notFoundException) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", notFoundException.getMessage());
        response.put("status", NOT_FOUND.value());
        return new ResponseEntity<>(response, NOT_FOUND);
    }
}
