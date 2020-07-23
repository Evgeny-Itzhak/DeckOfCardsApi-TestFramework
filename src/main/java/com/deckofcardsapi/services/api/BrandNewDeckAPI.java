package com.deckofcardsapi.services.api;

import com.deckofcardsapi.dto.BrandNewDeckDTO;
import com.deckofcardsapi.services.ResponseService;
import com.deckofcardsapi.services.request.GetBrandNewDeckRequest;
import com.deckofcardsapi.services.response.RESTResponse;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrandNewDeckAPI extends ResponseService {

    @Step("GET BrandNewDeck")
    public RESTResponse<BrandNewDeckDTO> brandNewDeck() {
        log.debug("Sending a request to get a Brand New Deck");
        GetBrandNewDeckRequest request = new GetBrandNewDeckRequest();
        return sendRequest(request, BrandNewDeckDTO.class);
    }
}