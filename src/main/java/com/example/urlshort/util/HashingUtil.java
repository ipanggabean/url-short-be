package com.example.urlshort.util;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class HashingUtil {

    public static String hash(final String seed) {
        return Hashing.murmur3_32().hashString(seed, StandardCharsets.UTF_8).toString();
    }
}
