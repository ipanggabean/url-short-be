package com.example.urlshort.repository;

import com.example.urlshort.entity.UrlStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlStoreRepository extends PagingAndSortingRepository<UrlStore, String> {

    Page<UrlStore> findByIsActiveTrueAndUrlContainingIgnoreCase(String url, Pageable pageable);

    Page<UrlStore> findByIsActiveTrue(Pageable pageable);

    Optional<UrlStore> findByIdAndIsActiveTrue(String id);
}
