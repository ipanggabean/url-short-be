package com.example.urlshort.service;

import com.example.urlshort.dto.UrlDTO;
import com.example.urlshort.entity.UrlStore;
import com.example.urlshort.mapper.UrlDTOMapper;
import com.example.urlshort.repository.UrlStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UrlAdminService {

    @Autowired
    private UrlStoreRepository urlStoreRepository;

    public Page<UrlDTO> findPage(Pageable pageable, String search) {
        Page<UrlStore> result;

        if (StringUtils.hasText(search)) {
            result = urlStoreRepository.findByIsActiveTrueAndUrlContainingIgnoreCase(search, pageable);
        } else {
            result = urlStoreRepository.findByIsActiveTrue(pageable);
        }

        return result.map(urlStore -> UrlDTOMapper.urlStoreToUrlDTO(urlStore));
    }

    public void disableById(String id) {
        urlStoreRepository.findByIdAndIsActiveTrue(id).ifPresent(urlStore -> {
            urlStore.setActive(false);
            urlStoreRepository.save(urlStore);
        });
    }
}
