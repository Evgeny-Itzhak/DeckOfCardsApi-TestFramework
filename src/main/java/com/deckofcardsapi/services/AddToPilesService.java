package com.deckofcardsapi.services;

import com.deckofcardsapi.services.api.AddToPilesAPI;

public class AddToPilesService extends ResponseService {

    private AddToPilesAPI addToPilesAPI;

    public AddToPilesService() {
        this.addToPilesAPI = new AddToPilesAPI();
    }

    public AddToPilesAPI getAddToPilesAPI() {
        return addToPilesAPI;
    }
}