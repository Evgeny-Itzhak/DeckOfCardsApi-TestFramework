package com.deckofcardsapi.services;

import com.deckofcardsapi.services.api.DrawFromPileBottomAPI;

public class DrawFromPileBottomService extends ResponseService {

    private DrawFromPileBottomAPI drawFromPileBottomAPI;

    public DrawFromPileBottomService() {
        this.drawFromPileBottomAPI = new DrawFromPileBottomAPI();
    }

    public DrawFromPileBottomAPI getDrawFromPileBottomAPI() {
        return drawFromPileBottomAPI;
    }
}