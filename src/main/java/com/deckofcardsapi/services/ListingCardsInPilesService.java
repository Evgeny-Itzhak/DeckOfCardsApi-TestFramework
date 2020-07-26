package com.deckofcardsapi.services;

import com.deckofcardsapi.services.api.ListingCardsInPilesAPI;

public class ListingCardsInPilesService extends ResponseService {

    private ListingCardsInPilesAPI listingCardsInPilesAPI;

    public ListingCardsInPilesService() {
        this.listingCardsInPilesAPI = new ListingCardsInPilesAPI();
    }

    public ListingCardsInPilesAPI getListingCardsInPilesAPI() {
        return listingCardsInPilesAPI;
    }
}