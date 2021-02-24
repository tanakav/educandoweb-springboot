package com.educandoweb.api.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import com.educandoweb.api.services.exceptions.ObjectNotFoundException;
import com.educandoweb.api.services.exceptions.StandardError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(
        ObjectNotFoundException e,
         HttpServletRequest request
         ){

        StandardError error = new StandardError(
            HttpStatus.NOT_FOUND.value(),
            e.getMessage(),
            System.currentTimeMillis());
        
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
