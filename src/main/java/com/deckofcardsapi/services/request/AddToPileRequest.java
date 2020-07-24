package com.deckofcardsapi.services.request;

import com.deckofcardsapi.utils.enums.EndpointUrl;
import com.deckofcardsapi.utils.enums.HttpMethod;

import java.util.HashMap;

public class AddToPileRequest extends AbstractRequest {

    public AddToPileRequest(String deckId, HashMap<String, Object> params, String pileName) {
        super(EndpointUrl.PILE, HttpMethod.GET, deckId, pileName, EndpointUrl.ADD);
        setQueryParameters(params);
    }
}