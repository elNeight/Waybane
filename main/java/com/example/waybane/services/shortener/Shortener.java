package com.example.waybane.services.shortener;

@FunctionalInterface
public interface Shortener {

    String shorten(String originalLink, Long tail);

}
