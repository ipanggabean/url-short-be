package com.example.urlshort.controller;

import com.example.urlshort.entity.UrlStore;
import com.example.urlshort.service.UrlShortenerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/short")
public class ShortenerController {

    @Autowired
    UrlShortenerService urlShortenerService;

    @PostMapping
    public ResponseEntity create(@RequestBody final String url) {
        // Validating the URL
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (!urlValidator.isValid(url)) {
            throw new RuntimeException("Invalid URL");
        }

        // Process the URL on valid
        UrlStore urlStore = urlShortenerService.makeShort(url);

        return ResponseEntity.ok(urlStore.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity getUrl(@PathVariable final String id) {
        // Get from DB
        UrlStore urlStore = urlShortenerService.getActiveById(id);

        // increment the counter with the hit
        urlStore = urlShortenerService.increaseHit(urlStore);

        return ResponseEntity.ok(urlStore.getUrl());
    }

}
