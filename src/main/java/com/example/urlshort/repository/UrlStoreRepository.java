package com.example.urlshort.repository;

import com.example.urlshort.entity.UrlStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlStoreRepository extends JpaRepository<UrlStore, String> {
}
