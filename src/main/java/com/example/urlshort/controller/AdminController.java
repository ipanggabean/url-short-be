package com.example.urlshort.controller;

import com.example.urlshort.dto.UrlDTO;
import com.example.urlshort.service.UrlAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UrlAdminService urlAdminService;

    @GetMapping
    public Page<UrlDTO> findPaginated(@PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String search) {
        return urlAdminService.findPage(pageable, search);
    }
}
