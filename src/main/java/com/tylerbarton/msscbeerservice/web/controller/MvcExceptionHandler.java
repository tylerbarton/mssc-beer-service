package com.tylerbarton.msscbeerservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Global exception handler for controllers
 */
@ControllerAdvice
public class MvcExceptionHandler {

    /**
     * Validation constraint error for when a failed validation is caught
     * @param e Exception
     * @return Response with an array of error messages and BAD REQUEST status
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validatedErrorHandler(ConstraintViolationException e){
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());      // Initialize to the size of the exception list
        e.getConstraintViolations().forEach(error -> {errors.add(error.toString());});  // Would have more refined error handling in deployment
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);                          // Return list of errors and BAD REQUEST status
    }

}
