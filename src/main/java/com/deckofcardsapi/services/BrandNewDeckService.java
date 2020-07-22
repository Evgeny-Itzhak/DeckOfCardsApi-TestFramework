package com.deckofcardsapi.services;

import com.deckofcardsapi.services.api.BrandNewDeckAPI;

public class BrandNewDeckService extends ResponseService {

    private BrandNewDeckAPI brandNewDeckAPI;

    public BrandNewDeckService() {
        this.brandNewDeckAPI = new BrandNewDeckAPI();
    }

    public BrandNewDeckAPI getBrandNewDeckAPI() {
        return brandNewDeckAPI;
    }
}