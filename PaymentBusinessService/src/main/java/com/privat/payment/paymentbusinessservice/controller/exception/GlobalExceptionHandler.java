package com.privat.payment.paymentbusinessservice.controller.exception;

import com.privat.payment.paymentbusinessservice.client.exception.EntityNotFoundException;
import com.privat.payment.paymentbusinessservice.exception.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNoUpdateRequiredException(EntityNotFoundException ex, WebRequest request) {
        ExceptionDetails errorDetails = new ExceptionDetails(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
