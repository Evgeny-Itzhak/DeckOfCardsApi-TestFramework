package com.deckofcardsapi.services.api;

import com.deckofcardsapi.dto.PartialDeckDTO;
import com.deckofcardsapi.services.ResponseService;
import com.deckofcardsapi.services.request.PartialDeckRequest;
import com.deckofcardsapi.services.response.RESTResponse;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class PartialDeckAPI extends ResponseService {

    @Step("GET Partial Deck")
    public RESTResponse<PartialDeckDTO> partialDeck(HashMap<String, Object> params) {
        log.debug("Sending a request to get a Partial Deck");
        PartialDeckRequest request = new PartialDeckRequest(params);
        return sendRequest(request, PartialDeckDTO.class);
    }
}