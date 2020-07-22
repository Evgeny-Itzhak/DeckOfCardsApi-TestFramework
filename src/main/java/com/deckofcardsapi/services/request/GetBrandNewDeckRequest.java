package com.deckofcardsapi.services.request;


import com.deckofcardsapi.utils.enums.EndpointUrl;
import com.deckofcardsapi.utils.enums.HttpMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetBrandNewDeckRequest extends AbstractRequest {

    public GetBrandNewDeckRequest() {
        super(EndpointUrl.NEW, HttpMethod.GET);
    }
}