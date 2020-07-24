package com.deckofcardsapi.services.api;

import com.deckofcardsapi.dto.DrawCardDTO;
import com.deckofcardsapi.services.ResponseService;
import com.deckofcardsapi.services.request.DrawCardRequest;
import com.deckofcardsapi.services.response.RESTResponse;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class DrawCardAPI extends ResponseService {

    @Step("GET Draw a Card")
    public RESTResponse<DrawCardDTO> drawCard(String deckId, HashMap<String, Object> params) {
        log.debug("Sending a request to Draw a Card");
        DrawCardRequest request = new DrawCardRequest(deckId, params);
        return sendRequest(request, DrawCardDTO.class);
    }

    @Step("GET Draw a Card from new deck")
    public RESTResponse<DrawCardDTO> drawCard(HashMap<String, Object> params) {
        log.debug("Sending a request to Draw a Card from new deck");
        DrawCardRequest request = new DrawCardRequest(params);
        return sendRequest(request, DrawCardDTO.class);
    }
}