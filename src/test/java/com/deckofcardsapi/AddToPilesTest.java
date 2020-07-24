package com.deckofcardsapi;

import com.deckofcardsapi.dto.BrandNewDeckDTO;
import com.deckofcardsapi.dto.DrawCardDTO;
import com.deckofcardsapi.services.AddToPilesService;
import com.deckofcardsapi.services.DrawCardService;
import com.deckofcardsapi.services.response.RESTResponse;
import com.deckofcardsapi.utils.enums.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Slf4j
public class AddToPilesTest {

    private DrawCardService drawCardService = new DrawCardService();
    private AddToPilesService addToPilesService = new AddToPilesService();


    @Test(description = "[Adding to Piles] Add drawn cards to the pile")
    public void addToPiles() {

        int numberOfCardsToDraw = 5;
        HashMap<String, Object> params = new HashMap<>();
        params.put("count", numberOfCardsToDraw);

        log.info("Get Draw a Card from the new deck request");
        RESTResponse<DrawCardDTO> drawCard = drawCardService.getDrawCardAPI().drawCard(params);
        assertEquals(drawCard.getStatus(), HttpStatus.OK);

        List<String> drawnCards = new ArrayList<>();
        drawCard.getResponseBean().getCards().forEach(card -> drawnCards.add(card.getCode()));

        params.clear();
        params.put("cards", drawnCards);

        String deckId = drawCard.getResponseBean().getDeckId();
        String pileName = "testpile";

        log.info("Get Add to Piles request");
        RESTResponse<BrandNewDeckDTO> addToPiles = addToPilesService.getAddToPilesAPI().addToPiles(deckId, params, pileName);
        assertEquals(drawCard.getStatus(), HttpStatus.OK);

        log.info("Validate the number of added cards to the pile");
        String jsonResponse = addToPiles.getRestResponse().jsonPath().getMap("piles").get("testpile").toString();
        int actualNumberOfRemainingCardsInPile = Integer.parseInt(jsonResponse.split("=")[1].replace("}", ""));
        assertEquals(actualNumberOfRemainingCardsInPile, numberOfCardsToDraw, "Wrong number of remaining cards in the pile");
    }
}