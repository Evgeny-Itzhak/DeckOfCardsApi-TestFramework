package com.deckofcardsapi.services.api;

import com.deckofcardsapi.dto.DrawCardDTO;
import com.deckofcardsapi.services.ResponseService;
import com.deckofcardsapi.services.request.DrawFromPileBottomRequest;
import com.deckofcardsapi.services.response.RESTResponse;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class DrawFromPileBottomAPI extends ResponseService {

    @Step("GET DrawFromPileBottom")
    public RESTResponse<DrawCardDTO> drawFromPileBottom(String deckId, String pileName, HashMap<String, Object> params) {
        log.debug("Sending a request to draw cards from the bottom of the pile");
        DrawFromPileBottomRequest request = new DrawFromPileBottomRequest(deckId, pileName, params);
        return sendRequest(request, DrawCardDTO.class);
    }
}