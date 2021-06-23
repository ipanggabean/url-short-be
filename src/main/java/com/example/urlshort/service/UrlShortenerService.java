package com.example.urlshort.service;

import com.example.urlshort.entity.UrlStore;
import com.example.urlshort.exception.ShortURLInvalidException;
import com.example.urlshort.repository.UrlStoreRepository;
import com.example.urlshort.util.HashingUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UrlShortenerService {

    private final long DEFAULT_EXPIRATION_DAY = 365;

    @Autowired
    private UrlStoreRepository urlStoreRepository;

    public UrlStore makeShort(String url) {
        log.debug("Received URL to be shortened: {}", url);

        UrlStore urlStore = make(url);
        return urlStoreRepository.save(urlStore);
    }

    public UrlStore getActiveById(final String id) {
        return urlStoreRepository.findById(id)
                .filter(UrlStore::isActive)
                .filter(urlStore -> urlStore.getExpiredTime().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new ShortURLInvalidException("Shortened URL is not valid"));
    }

    public UrlStore increaseHit(UrlStore urlStore) {
        urlStore.increaseHit();
        return urlStoreRepository.save(urlStore);
    }

    private UrlStore make(@NonNull String url) {
        return UrlStore.builder()
                .id(HashingUtil.hash(url))
                .url(url)
                .hit(0)
                .expiredTime(LocalDateTime.now().plusDays(DEFAULT_EXPIRATION_DAY))
                .isActive(true)
                .build();
    }

}
