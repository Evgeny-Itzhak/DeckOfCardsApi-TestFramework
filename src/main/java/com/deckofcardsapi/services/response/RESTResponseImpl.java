package com.deckofcardsapi.services.response;

import com.deckofcardsapi.utils.enums.HttpStatus;
import io.restassured.response.Response;
import org.json.JSONTokener;

public class RESTResponseImpl<T> implements RESTResponse<T> {

    private String responseBody;
    private T bean;
    private Response restResponse;
    private HttpStatus httpStatus;

    public RESTResponseImpl(Response response) {
        restResponse = response;
        responseBody = response.getBody().asString();
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.get(restResponse.getStatusCode());
    }

    @Override
    public T getResponseBean() {
        return bean;
    }

    @Override
    public void setResponseBean(T responseObject) {
        this.bean = responseObject;
    }

    @Override
    public String getBody() {
        return new JSONTokener(responseBody).toString();
    }

    @Override
    public Response getRestResponse() {
        return restResponse;
    }
}