package com.deckofcardsapi;

import com.deckofcardsapi.dto.BrandNewDeckDTO;
import com.deckofcardsapi.services.BrandNewDeckService;
import com.deckofcardsapi.services.response.RESTResponse;
import com.deckofcardsapi.utils.enums.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Slf4j
public class GetBrandNewDeckTest {

    BrandNewDeckService brandNewDeckService = new BrandNewDeckService();

    @Test(description = "[A Brand New Deck] Open a brand new deck of cards")
    public void getBrandNewDeck() {
        log.info("Get Brand New Deck");
        RESTResponse<BrandNewDeckDTO> getBrandNewDeck = brandNewDeckService.getBrandNewDeckAPI().getBrandNewDeck();
        assertEquals(getBrandNewDeck.getStatus(), HttpStatus.OK);
    }
}