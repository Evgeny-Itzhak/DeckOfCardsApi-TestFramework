package com.deckofcardsapi.services.request;

import com.deckofcardsapi.utils.enums.EndpointUrl;
import com.deckofcardsapi.utils.enums.HttpMethod;

import java.util.HashMap;

public class DrawFromPileBottomRequest extends AbstractRequest {

    public DrawFromPileBottomRequest(String deckId, String pileName, HashMap<String, Object> params) {
        super(EndpointUrl.PILE, HttpMethod.GET, deckId, pileName, EndpointUrl.DRAW_BOTTOM);
        setQueryParameters(params);
    }
}