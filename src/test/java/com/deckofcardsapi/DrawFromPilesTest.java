package com.deckofcardsapi;

import com.deckofcardsapi.dto.BrandNewDeckDTO;
import com.deckofcardsapi.dto.DrawCardDTO;
import com.deckofcardsapi.services.DrawFromPileBottomService;
import com.deckofcardsapi.services.response.RESTResponse;
import com.deckofcardsapi.utils.helpers.DeckHelper;
import com.deckofcardsapi.utils.helpers.PilesHelper;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Slf4j
public class DrawFromPilesTest {

    private DrawFromPileBottomService drawFromPileBottomService = new DrawFromPileBottomService();
    private DeckHelper deckHelper = new DeckHelper();
    private PilesHelper pilesHelper = new PilesHelper();

    private HashMap<String, Object> params = new HashMap<>();
    private int actualNumberOfRemainingCardsInPile = 0;
    private int numberOfCardsToDrawFromTheBottom = 5;

    private String deckId;
    private final String PILE_NAME = "testpile";


    @BeforeClass
    public void prepareTestData() {
        int numberOfCardsToDraw = 52;
        params.put("count", numberOfCardsToDraw);

        log.info("Get Draw a Card from the new deck request");
        DrawCardDTO drawCard = deckHelper.getDrawCardFromNewDeck(params);
        List<String> drawnCards = deckHelper.getCardsFromDrawCardFromExistingDeck(drawCard);
        params.clear();
        params.put("cards", drawnCards);

        deckId = drawCard.getDeckId();

        RESTResponse<BrandNewDeckDTO> addToPiles = pilesHelper.addToPiles(deckId, params, PILE_NAME);

        log.info("Validate the number of added cards to the pile");
        String jsonResponse = addToPiles.getRestResponse().jsonPath().getMap("piles").get(PILE_NAME).toString();
        actualNumberOfRemainingCardsInPile = Integer.parseInt(jsonResponse.split("=")[1].replace("}", ""));
        assertEquals(actualNumberOfRemainingCardsInPile, numberOfCardsToDraw, "Wrong number of remaining cards in the pile");

        params.clear();
        params.put("count", numberOfCardsToDrawFromTheBottom);

    }

    @Test(description = "[Drawing from Piles] Draw cards from the bottom of the pile")
    public void drawFromPileBottom() {

        log.info("Get Draw cards from the bottom of the pile");
        RESTResponse<DrawCardDTO> drawCardsFromTheBottomOFThePile = drawFromPileBottomService.getDrawFromPileBottomAPI().drawFromPileBottom(deckId, PILE_NAME, params);

        log.info("Validate that the number of cards in the pile is decreased");
        String jsonResponseAfterDraw = drawCardsFromTheBottomOFThePile.getRestResponse().jsonPath().getMap("piles").get(PILE_NAME).toString();
        int actualNumberOfRemainingCardsInPileAfterDraw = Integer.parseInt(jsonResponseAfterDraw.split("=")[1].replace("}", ""));
        int numberOfCardsAfterDraw = actualNumberOfRemainingCardsInPile - numberOfCardsToDrawFromTheBottom;

        assertEquals(actualNumberOfRemainingCardsInPileAfterDraw, numberOfCardsAfterDraw, "Wrong number of remaining cards in the pile");
    }
}