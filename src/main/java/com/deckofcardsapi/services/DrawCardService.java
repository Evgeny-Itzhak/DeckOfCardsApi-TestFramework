package com.deckofcardsapi.services;

import com.deckofcardsapi.services.api.DrawCardAPI;

public class DrawCardService extends ResponseService {

    private DrawCardAPI drawCardAPI;

    public DrawCardService() {
        this.drawCardAPI = new DrawCardAPI();
    }

    public DrawCardAPI getDrawCardAPI() {
        return drawCardAPI;
    }
}