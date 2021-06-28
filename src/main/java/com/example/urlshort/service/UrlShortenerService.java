package com.example.urlshort.service;

import com.example.urlshort.dto.UrlDTO;
import com.example.urlshort.entity.UrlStore;
import com.example.urlshort.exception.ShortURLInvalidException;
import com.example.urlshort.repository.UrlStoreRepository;
import com.example.urlshort.util.HashingUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
@Slf4j
public class UrlShortenerService {

    private final long DEFAULT_EXPIRATION_DAY = 365;

    @Autowired
    private UrlStoreRepository urlStoreRepository;

    public UrlStore makeShort(UrlDTO urlDTO) {
        log.debug("Received URL to be shortened: {}", urlDTO.getUrl());

        UrlStore urlStore = make(urlDTO);
        return urlStoreRepository.save(urlStore);
    }

    public UrlStore getActiveById(final String id) {
        return urlStoreRepository.findByIdAndIsActiveTrue(id)
                .filter(urlStore -> urlStore.getExpiredTime().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new ShortURLInvalidException("Shortened URL is not valid"));
    }

    public UrlStore increaseHit(UrlStore urlStore) {
        urlStore.increaseHit();
        return urlStoreRepository.save(urlStore);
    }

    private UrlStore make(@NonNull UrlDTO urlDTO) {
        return UrlStore.builder()
                .id(HashingUtil.hash(urlDTO.getUrl()))
                .url(urlDTO.getUrl())
                .hit(0)
                .expiredTime(urlDTO.getExpiredTime() != null ? urlDTO.getExpiredTime() : LocalDateTime.now().plusDays(DEFAULT_EXPIRATION_DAY))
                .isActive(true)
                .build();
    }

}
