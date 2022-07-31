package com.example.waybane.services.shortener;


import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDShortener implements Shortener {

    @Override
    public String shorten(String originalLink, Long tail ) {
        return UUID.nameUUIDFromBytes((originalLink + tail).getBytes())
                .toString()
                .substring(0, 7);
    }

}
