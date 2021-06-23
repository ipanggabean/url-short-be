package com.example.urlshort.mapper;

import com.example.urlshort.dto.UrlDTO;
import com.example.urlshort.entity.UrlStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UrlDTOMapper {

    public static UrlDTO urlStoreToUrlDTO(UrlStore urlStore) {
        return UrlDTO.builder()
                .alias(urlStore.getId())
                .url(urlStore.getUrl())
                .expiredTime(urlStore.getExpiredTime())
                .hit(urlStore.getHit())
                .build();
    }
}
