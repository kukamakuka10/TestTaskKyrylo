package com.privat.payment.paymentbusinessservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ExceptionDetails {
    private HttpStatus status;
    private String message;
    private String details;

    public ExceptionDetails(HttpStatus status, String message, String details) {
        super();
        this.status = status;
        this.message = message;
        this.details = details;
    }
}
