package com.deckofcardsapi;

import com.deckofcardsapi.dto.BrandNewDeckDTO;
import com.deckofcardsapi.dto.DrawCardDTO;
import com.deckofcardsapi.services.BrandNewDeckService;
import com.deckofcardsapi.services.DrawCardService;
import com.deckofcardsapi.services.response.RESTResponse;
import com.deckofcardsapi.utils.enums.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

@Slf4j
public class GetDrawCardTest {

    private BrandNewDeckService brandNewDeckService = new BrandNewDeckService();
    private DrawCardService drawCardService = new DrawCardService();

    @DataProvider(name = "queryParameter")
    public Object[][] dataProvider() {
        return new Object[][]{{53}, {10}, {52}};
    }

    @Test(description = "[Draw a Card] Draw specified number of cards from the deck", dataProvider = "queryParameter")
    public void getDrawCard(int numberOfCardsToDraw) {

        log.info("Get Brand New Deck");
        RESTResponse<BrandNewDeckDTO> getBrandNewDeck = brandNewDeckService.getBrandNewDeckAPI().getBrandNewDeck();
        assertEquals(getBrandNewDeck.getStatus(), HttpStatus.OK);

        String deckId = getBrandNewDeck.getResponseBean().getDeckId();
        HashMap<String, Object> params = new HashMap<>();
        params.put("count", numberOfCardsToDraw);

        log.info("Get Draw a Card");
        RESTResponse<DrawCardDTO> getDrawCard = drawCardService.getDrawCardAPI().drawCard(deckId, params);
        assertEquals(getDrawCard.getStatus(), HttpStatus.OK);

        int numberOfCardsInTheBrandNewDeck = getBrandNewDeck.getResponseBean().getRemaining();
        int numberOfCardsAfterDraw = numberOfCardsInTheBrandNewDeck - numberOfCardsToDraw;
        int actualNumberOfCardsAfterDraw = getDrawCard.getResponseBean().getRemaining();

        assertEquals(actualNumberOfCardsAfterDraw, numberOfCardsAfterDraw, "Wrong number of remaining cards after draw");
    }
}