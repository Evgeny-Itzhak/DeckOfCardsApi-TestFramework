package com.deckofcardsapi.utils.enums;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public enum HttpMethod {

    GET("GET") {
        public Response sendRequest(String url, RequestSpecification request) {
            return request.get(url);
        }
    },
    PUT("GET") {
        public Response sendRequest(String url, RequestSpecification request) {
            return request.get(url);
        }
    },
    POST("GET") {
        public Response sendRequest(String url, RequestSpecification request) {
            return request.get(url);
        }
    },
    DELETE("GET") {
        public Response sendRequest(String url, RequestSpecification request) {
            return request.get(url);
        }
    };

    private final String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public abstract Response sendRequest(String url, RequestSpecification request);
}