package com.deckofcardsapi.services.request;

import com.deckofcardsapi.utils.enums.EndpointUrl;
import com.deckofcardsapi.utils.enums.HttpMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class PartialDeckRequest extends AbstractRequest {

    public PartialDeckRequest(HashMap<String, Object> params) {
        super(EndpointUrl.NEW_SHUFFLE, HttpMethod.GET);
        setQueryParameters(params);
    }
}