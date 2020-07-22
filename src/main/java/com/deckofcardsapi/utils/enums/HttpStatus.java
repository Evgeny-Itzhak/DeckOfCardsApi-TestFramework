package com.deckofcardsapi.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum HttpStatus {
    OK(200),
    CREATED(201);

    private int statusCode;
    private static final Map<Integer, HttpStatus> lookup = new HashMap<>();

    static {
        for (HttpStatus httpStatus : HttpStatus.values()) {
            lookup.put(httpStatus.getStatusCode(), httpStatus);
        }
    }

    HttpStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public static HttpStatus get(int value) {
        return lookup.get(value);
    }
}