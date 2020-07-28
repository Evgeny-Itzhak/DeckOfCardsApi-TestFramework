package com.deckofcardsapi.services.request;

import com.deckofcardsapi.utils.enums.HttpMethod;

public class DeckInfoRequest extends AbstractRequest {
    public DeckInfoRequest(String deckId) {
        super(HttpMethod.GET, deckId);
    }
}