package com.deckofcardsapi.utils.helpers;

import com.deckofcardsapi.dto.BrandNewDeckDTO;
import com.deckofcardsapi.services.AddToPilesService;
import com.deckofcardsapi.services.response.RESTResponse;
import com.deckofcardsapi.utils.enums.HttpStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

@Slf4j
public class PilesHelper {

    private AddToPilesService addToPilesService = new AddToPilesService();

    public RESTResponse<BrandNewDeckDTO> addToPiles(String deckId, HashMap<String, Object> params, String pileName) {
        log.info("Get Add to Piles request");
        RESTResponse<BrandNewDeckDTO> response = addToPilesService.getAddToPilesAPI().addToPiles(deckId, params, pileName);
        assertEquals(response.getStatus(), HttpStatus.OK);
        return response;
    }
}