package com.deckofcardsapi;

import com.deckofcardsapi.dto.BrandNewDeckDTO;
import com.deckofcardsapi.dto.DrawCardDTO;
import com.deckofcardsapi.services.DrawFromPileBottomService;
import com.deckofcardsapi.services.ListingCardsInPilesService;
import com.deckofcardsapi.services.response.RESTResponse;
import com.deckofcardsapi.utils.helpers.DeckHelper;
import com.deckofcardsapi.utils.helpers.PilesHelper;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Epic("TESTING for http://deckofcardsapi.com/ API")
@Feature(value = "Drawing from Piles endpoint")
@Slf4j
public class DrawFromPilesTest {

    private DrawFromPileBottomService drawFromPileBottomService = new DrawFromPileBottomService();
    private ListingCardsInPilesService listingCardsInPilesService = new ListingCardsInPilesService();
    private DeckHelper deckHelper = new DeckHelper();
    private PilesHelper pilesHelper = new PilesHelper();

    private HashMap<String, Object> params = new HashMap<>();
    private int actualNumberOfRemainingCardsInPile = 0;
    private int numberOfCardsToDrawFromTheBottom = 5;

    private String deckId;
    private final String PILE_NAME = "testpile";
    private final String PILES_PILE_NAME_REMAINING = "piles.testpile.remaining";
    private final String PILES_PILE_NAME_CARDS = "piles.testpile.cards";

    @BeforeClass
    public void prepareTestData() {
        int numberOfCardsToDraw = 52;
        params.put("count", numberOfCardsToDraw);

        DrawCardDTO drawnCards = deckHelper.getDrawCardFromNewDeck(params);
        List<String> drawnCardsList = deckHelper.getCardsFromDrawCardFromExistingDeck(drawnCards);
        params.clear();
        params.put("cards", drawnCardsList);

        deckId = drawnCards.getDeckId();

        RESTResponse<BrandNewDeckDTO> addToPiles = pilesHelper.addToPiles(deckId, params, PILE_NAME);

        log.info("Validate the number of added cards to the pile");
        String jsonResponse = addToPiles.getRestResponse().jsonPath().getMap("piles").get(PILE_NAME).toString();
        actualNumberOfRemainingCardsInPile = Integer.parseInt(jsonResponse.split("=")[1].replace("}", ""));
        assertEquals(actualNumberOfRemainingCardsInPile, numberOfCardsToDraw, "Wrong number of remaining cards in the pile");

        params.clear();
        params.put("count", numberOfCardsToDrawFromTheBottom);
    }

    @Story(value = "Draw cards from the bottom of the pile")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "[Drawing from Piles] Draw cards from the bottom of the pile")
    public void drawFromPileBottom() {

        log.info("Perform GET Draw cards from the bottom of the pile request");
        RESTResponse<DrawCardDTO> drawCardsFromTheBottomOFThePile = drawFromPileBottomService.getDrawFromPileBottomAPI().drawFromPileBottom(deckId, PILE_NAME, params);

        log.info("Validate that the number of cards in the pile is decreased");
        int actualNumberOfRemainingCardsInPileAfterDraw = drawCardsFromTheBottomOFThePile.getRestResponse().jsonPath().getInt(PILES_PILE_NAME_REMAINING);
        int numberOfCardsAfterDraw = actualNumberOfRemainingCardsInPile - numberOfCardsToDrawFromTheBottom;
        assertEquals(actualNumberOfRemainingCardsInPileAfterDraw, numberOfCardsAfterDraw, "Wrong number of remaining cards in the pile");

        log.info("Perform GET Listing Cards in Piles request");
        RESTResponse<BrandNewDeckDTO> listingCardsInPiles = listingCardsInPilesService.getListingCardsInPilesAPI().listingCardsInPiles(deckId, PILE_NAME);

        log.info("Validate that the pile does not contain drawn cards");
        List<LinkedHashMap<String, String>> remainedCardsInPileMap = listingCardsInPiles.getRestResponse().jsonPath().getList(PILES_PILE_NAME_CARDS);

        List<String> remainedCardsInPileList = new ArrayList<>();
        remainedCardsInPileMap.forEach(card -> remainedCardsInPileList.add(card.get("code")));

        List<String> drawnCardsFromTheBottom = new ArrayList<>();
        drawCardsFromTheBottomOFThePile.getResponseBean().getCards().forEach(card -> drawnCardsFromTheBottom.add(card.getCode()));

        drawnCardsFromTheBottom.forEach(drawnCard -> {
            assertFalse(remainedCardsInPileList.contains(drawnCard), String.format("Pile contains drawn card. Card code: %s.", drawnCard));
        });
    }
}