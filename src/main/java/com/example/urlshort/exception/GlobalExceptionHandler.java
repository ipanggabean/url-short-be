package com.example.urlshort.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(InvalidURLException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public void handle(InvalidURLException e) {
//        log.error(e.getLocalizedMessage());
//    }
//
//    @ExceptionHandler(ShortURLInvalidException.class)
//    @ResponseStatus(HttpStatus.GONE)
//    @ResponseBody
//    public void handle(ShortURLInvalidException e) {
//        log.error(e.getLocalizedMessage());
//    }
}
