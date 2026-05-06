package com.doctime.identity.adapter.inbound.web;

import com.doctime.identity.adapter.inbound.web.DTO.ApiError;
import com.doctime.identity.core.exceptions.UserAlreadyExistsException;
import com.doctime.identity.core.exceptions.UserCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExist(UserAlreadyExistsException exception) {
        ApiError error = new ApiError("USER_ALREADY_EXIST", exception.getMessage(), 409);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<ApiError> handleUserCreation(UserCreationException exception) {
        ApiError error = new ApiError("USER_CREATION", exception.getMessage(), 500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception exception) {
        ApiError error = new ApiError("INTERNAL_SERVER_ERROR", "An unexpected error occurred", 500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}