package com.deckofcardsapi.services.request;


import com.deckofcardsapi.utils.enums.EndpointUrl;
import com.deckofcardsapi.utils.enums.HttpMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class GetDrawCardRequest extends AbstractRequest {

    public GetDrawCardRequest(String deckId, HashMap<String, Object> params) {
        super(EndpointUrl.DRAW, HttpMethod.GET, deckId);
        setQueryParameters(params);
    }

    public GetDrawCardRequest(HashMap<String, Object> params) {
        super(EndpointUrl.NEW_DRAW, HttpMethod.GET);
        setQueryParameters(params);
    }
}