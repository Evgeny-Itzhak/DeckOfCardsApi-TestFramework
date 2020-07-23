package com.deckofcardsapi.services;

import com.deckofcardsapi.services.api.PartialDeckAPI;

public class PartialDeckService extends ResponseService {

    private PartialDeckAPI partialDeckAPI;

    public PartialDeckService() {
        this.partialDeckAPI = new PartialDeckAPI();
    }

    public PartialDeckAPI getPartialDeckAPI() {
        return partialDeckAPI;
    }
}