package com.deckofcardsapi.services.request;

import com.deckofcardsapi.utils.enums.EndpointUrl;
import com.deckofcardsapi.utils.enums.HttpMethod;

public class ListingCardsInPilesRequest extends AbstractRequest {

    public ListingCardsInPilesRequest(String deckId, String pileName) {
        super(EndpointUrl.PILE, HttpMethod.GET, deckId, pileName, EndpointUrl.LIST);
    }
}