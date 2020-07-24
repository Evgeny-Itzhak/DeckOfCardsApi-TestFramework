package com.deckofcardsapi.services.request;


import com.deckofcardsapi.utils.enums.EndpointUrl;
import com.deckofcardsapi.utils.enums.HttpMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrandNewDeckRequest extends AbstractRequest {

    public BrandNewDeckRequest() {
        super(EndpointUrl.NEW, HttpMethod.GET);
    }
}