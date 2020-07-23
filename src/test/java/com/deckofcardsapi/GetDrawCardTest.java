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
        return new Object[][]{{5}, {10}, {52}};
    }

    @Test(description = "[Draw a Card] Draw specified number of cards from the deck", dataProvider = "queryParameter")
    public void getDrawCard(int numberOfCardsToDraw) {

        log.info("Get Brand New Deck");
        RESTResponse<BrandNewDeckDTO> brandNewDeck = brandNewDeckService.getBrandNewDeckAPI().brandNewDeck();
        assertEquals(brandNewDeck.getStatus(), HttpStatus.OK);

        String deckId = brandNewDeck.getResponseBean().getDeckId();
        HashMap<String, Object> params = new HashMap<>();
        params.put("count", numberOfCardsToDraw);

        log.info("Get Draw a Card");
        RESTResponse<DrawCardDTO> drawCard = drawCardService.getDrawCardAPI().drawCard(deckId, params);
        assertEquals(drawCard.getStatus(), HttpStatus.OK);

        int numberOfCardsInTheBrandNewDeck = brandNewDeck.getResponseBean().getRemaining();
        int numberOfCardsAfterDraw = numberOfCardsInTheBrandNewDeck - numberOfCardsToDraw;
        int actualNumberOfCardsAfterDraw = drawCard.getResponseBean().getRemaining();

        assertEquals(actualNumberOfCardsAfterDraw, numberOfCardsAfterDraw, "Wrong number of remaining cards after draw");
    }
}