package com.deckofcardsapi.services.api;

import com.deckofcardsapi.dto.PartialDeckDTO;
import com.deckofcardsapi.services.ResponseService;
import com.deckofcardsapi.services.request.GetPartialDeckRequest;
import com.deckofcardsapi.services.response.RESTResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class PartialDeckAPI extends ResponseService {

    public RESTResponse<PartialDeckDTO> partialDeck(HashMap<String, Object> params) {
        log.debug("Sending a request to get a Partial Deck");
        GetPartialDeckRequest request = new GetPartialDeckRequest(params);
        return sendRequest(request, PartialDeckDTO.class);
    }
}