package com.deckofcardsapi.services.api;

import com.deckofcardsapi.dto.BrandNewDeckDTO;
import com.deckofcardsapi.services.ResponseService;
import com.deckofcardsapi.services.request.GetAddToPileRequest;
import com.deckofcardsapi.services.response.RESTResponse;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class AddToPilesAPI extends ResponseService {

    @Step("GET AddToPiles")
    public RESTResponse<BrandNewDeckDTO> addToPiles(String deckId, HashMap<String, Object> params, String pileName) {
        log.debug("Sending a request to add drawn cards to the pile");
        GetAddToPileRequest request = new GetAddToPileRequest(deckId, params, pileName);
        return sendRequest(request, BrandNewDeckDTO.class);
    }
}