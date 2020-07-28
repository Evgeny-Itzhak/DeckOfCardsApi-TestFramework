package com.deckofcardsapi.services.api;

import com.deckofcardsapi.dto.BrandNewDeckDTO;
import com.deckofcardsapi.dto.DrawCardDTO;
import com.deckofcardsapi.services.ResponseService;
import com.deckofcardsapi.services.request.DeckInfoRequest;
import com.deckofcardsapi.services.request.DrawCardRequest;
import com.deckofcardsapi.services.response.RESTResponse;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class DeckInfoAPI extends ResponseService {

    @Step("GET Deck information")
    public RESTResponse<BrandNewDeckDTO> getDeckInformation(String deckId) {
        log.debug("Sending a request to get Deck information");
        DeckInfoRequest request = new DeckInfoRequest(deckId);
        return sendRequest(request, BrandNewDeckDTO.class);
    }
}