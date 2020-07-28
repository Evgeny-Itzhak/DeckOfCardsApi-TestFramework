package com.deckofcardsapi.services;

import com.deckofcardsapi.services.api.DeckInfoAPI;

public class DeckInfoService extends ResponseService {

    private DeckInfoAPI deckInfoAPI;

    public DeckInfoService() {
        this.deckInfoAPI = new DeckInfoAPI();
    }

    public DeckInfoAPI getDeckInfoAPI() {
        return deckInfoAPI;
    }
}