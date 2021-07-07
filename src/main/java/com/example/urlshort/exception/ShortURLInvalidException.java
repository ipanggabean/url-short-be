package com.example.urlshort.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class ShortURLInvalidException extends RuntimeException {
    public ShortURLInvalidException(String message) {
        super(message);
    }
}
