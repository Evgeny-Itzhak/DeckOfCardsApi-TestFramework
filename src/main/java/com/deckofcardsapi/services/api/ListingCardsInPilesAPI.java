package com.deckofcardsapi.services.api;

import com.deckofcardsapi.dto.BrandNewDeckDTO;
import com.deckofcardsapi.services.ResponseService;
import com.deckofcardsapi.services.request.ListingCardsInPilesRequest;
import com.deckofcardsapi.services.response.RESTResponse;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListingCardsInPilesAPI extends ResponseService {

    @Step("GET Listing Cards in Piles")
    public RESTResponse<BrandNewDeckDTO> listingCardsInPiles(String deckId, String pileName) {
        log.debug("Sending a request to Listing Cards in Piles");
        ListingCardsInPilesRequest request = new ListingCardsInPilesRequest(deckId, pileName);
        return sendRequest(request, BrandNewDeckDTO.class);
    }
}