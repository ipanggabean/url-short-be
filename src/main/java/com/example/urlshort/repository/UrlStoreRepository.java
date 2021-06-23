package com.example.urlshort.repository;

import com.example.urlshort.entity.UrlStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlStoreRepository extends PagingAndSortingRepository<UrlStore, String> {

    Page<UrlStore> findByUrlContainingIgnoreCase(String url, Pageable pageable);
}
