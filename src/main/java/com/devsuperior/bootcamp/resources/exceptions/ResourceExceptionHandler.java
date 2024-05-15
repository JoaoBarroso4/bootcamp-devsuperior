package com.devsuperior.bootcamp.resources.exceptions;

import com.devsuperior.bootcamp.services.exceptions.EntityAlreadyExists;
import com.devsuperior.bootcamp.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(generateStandardError(e, HttpStatus.NOT_FOUND, "Resource not found",
                        request.getRequestURI()));
    }

    @ExceptionHandler(EntityAlreadyExists.class)
    public ResponseEntity<StandardError> entityAlreadyExists(EntityAlreadyExists e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(generateStandardError(e, HttpStatus.BAD_REQUEST, "Entity already exists",
                        request.getRequestURI()));
    }

    private StandardError generateStandardError(Exception e, HttpStatus status, String error, String path) {
        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(status.value());
        standardError.setError(error);
        standardError.setMessage(e.getMessage());
        standardError.setPath(path);

        return standardError;
    }
}