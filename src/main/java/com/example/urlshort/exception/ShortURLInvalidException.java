package com.example.urlshort.exception;

public class ShortURLInvalidException extends RuntimeException {
    public ShortURLInvalidException(String message) {
        super(message);
    }
}
