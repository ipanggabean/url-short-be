package com.example.urlshort.controller;

import com.example.urlshort.dto.UrlDTO;
import com.example.urlshort.entity.UrlStore;
import com.example.urlshort.exception.InvalidURLException;
import com.example.urlshort.mapper.UrlDTOMapper;
import com.example.urlshort.service.UrlShortenerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@Slf4j
@RequestMapping("/short")
public class ShortenerController {

    @Autowired
    UrlShortenerService urlShortenerService;

    @PostMapping
    public ResponseEntity create(@RequestBody final UrlDTO urlDTO) {
        // Validating the URL
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (!urlValidator.isValid(urlDTO.getUrl())) {
            throw new InvalidURLException("Invalid URL");
        }

        // Process the URL on valid
        UrlStore urlStore = urlShortenerService.makeShort(urlDTO);

        return ResponseEntity.ok(UrlDTOMapper.urlStoreToUrlDTO(urlStore));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUrl(@PathVariable final String id) {
        // Get from DB
        UrlStore urlStore = urlShortenerService.getActiveById(id);

        // increment the counter with the hit
        urlStore = urlShortenerService.increaseHit(urlStore);

        return ResponseEntity.ok(UrlDTOMapper.urlStoreToUrlDTO(urlStore));

//        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlStore.getUrl())).build();
    }

}
