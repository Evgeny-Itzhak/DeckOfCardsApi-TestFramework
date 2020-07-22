package com.deckofcardsapi.services.response;

import com.deckofcardsapi.utils.enums.HttpStatus;
import io.restassured.response.Response;

public interface RESTResponse<T> {

    HttpStatus getStatus();

    T getResponseBean();

    void setResponseBean(T bean);

    String getBody();

    Response getRestResponse();
}