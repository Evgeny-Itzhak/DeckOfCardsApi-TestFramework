package com.deckofcardsapi.services;

import com.deckofcardsapi.services.request.AbstractRequest;
import com.deckofcardsapi.services.response.RESTResponse;
import com.deckofcardsapi.utils.enums.HttpStatus;

public class ResponseService {

    private final RESTHandler restHandler = new RESTHandler();

    public RESTResponse<?> sendRequest(AbstractRequest request) {
        return restHandler.sendRequest(request);
    }

    public <T> RESTResponse<T> sendRequest(AbstractRequest request, Class<T> responseBeanClass) {
        RESTResponse abstractResponse = restHandler.sendRequest(request);
        abstractResponse.setResponseBean(abstractResponse.getRestResponse().as(responseBeanClass));
        return abstractResponse;
    }

    public <T> void verifySuccessfulResponse(RESTResponse<T> response) {
        restHandler.verifyResponse(response, HttpStatus.OK);
    }

}