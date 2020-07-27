package com.deckofcardsapi;

import com.deckofcardsapi.dto.BrandNewDeckDTO;
import com.deckofcardsapi.dto.DrawCardDTO;
import com.deckofcardsapi.services.DrawCardService;
import com.deckofcardsapi.services.response.RESTResponse;
import com.deckofcardsapi.utils.enums.HttpStatus;
import com.deckofcardsapi.utils.helpers.DeckHelper;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

@Epic("TESTING for http://deckofcardsapi.com/ API")
@Feature(value = "Draw a card endpoint")
@Slf4j
public class DrawCardTest {

    private DrawCardService drawCardService = new DrawCardService();

    private DeckHelper deckHelper = new DeckHelper();

    @DataProvider(name = "queryParameter")
    public Object[][] dataProvider() {
        return new Object[][]{{5}, {10}, {52}};
    }

    @Story(value = "Draw a Card from the existing deck endpoint")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate if card count in the new deck is correct after drawing X cards from it")
    @Test(description = "[Draw a Card] Draw specified number of cards from the deck", dataProvider = "queryParameter")
    public void getDrawCard(int numberOfCardsToDraw) {

        log.info("Get Brand New Deck");
        BrandNewDeckDTO brandNewDeck = deckHelper.getBrandNewDeck();
        String deckId = brandNewDeck.getDeckId();
        HashMap<String, Object> params = new HashMap<>();
        params.put("count", numberOfCardsToDraw);

        log.info("Get Draw a Card");
        RESTResponse<DrawCardDTO> drawCard = drawCardService.getDrawCardAPI().drawCard(deckId, params);
        assertEquals(drawCard.getStatus(), HttpStatus.OK);

        int numberOfCardsInTheBrandNewDeck = brandNewDeck.getRemaining();
        int numberOfCardsAfterDraw = numberOfCardsInTheBrandNewDeck - numberOfCardsToDraw;
        int actualNumberOfCardsAfterDraw = drawCard.getResponseBean().getRemaining();

        log.info("Validate the number of drawn cards and remained cards in the deck after drawing");
        assertEquals(drawCard.getResponseBean().getCards().size(), numberOfCardsToDraw, "Wrong number of drawn cards in response");
        assertEquals(actualNumberOfCardsAfterDraw, numberOfCardsAfterDraw, "Wrong number of remaining cards after draw");
    }

    @Story(value = "Draw a Card from the new deck endpoint")
    @Description("Validate if card count in the new deck is correct after drawing X cards from it")
    @Test(description = "[Draw a Card] Draw specified number of cards from the new deck")
    @Severity(SeverityLevel.CRITICAL)
    public void getDrawCardFromNewDeck() {

        int numberOfCardsToDraw = RandomUtils.nextInt(1, 52 + 1);
        HashMap<String, Object> params = new HashMap<>();
        params.put("count", numberOfCardsToDraw);

        log.info("Get Draw a Card from the new deck");
        RESTResponse<DrawCardDTO> drawCard = drawCardService.getDrawCardAPI().drawCard(params);
        assertEquals(drawCard.getStatus(), HttpStatus.OK);

        log.info("Validate the number of drawn cards");
        assertEquals(drawCard.getResponseBean().getCards().size(), numberOfCardsToDraw, "Wrong number of drawn cards in response");
    }
}