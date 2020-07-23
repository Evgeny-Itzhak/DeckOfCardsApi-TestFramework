package com.deckofcardsapi;

import com.deckofcardsapi.dto.DrawCardDTO;
import com.deckofcardsapi.dto.PartialDeckDTO;
import com.deckofcardsapi.services.DrawCardService;
import com.deckofcardsapi.services.PartialDeckService;
import com.deckofcardsapi.services.response.RESTResponse;
import com.deckofcardsapi.utils.enums.Cards;
import com.deckofcardsapi.utils.enums.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

@Slf4j
public class GetPartialDeckTest {

    private PartialDeckService partialDeckService = new PartialDeckService();
    private DrawCardService drawCardService = new DrawCardService();


    @Test(description = "[Partial Deck] Create a new specified deck")
    public void getPartialDeck() {

        log.info("Prepare special cards for Partial Deck");
        List<String> specialCardsList = Arrays.asList(Cards.ACE_OF_DIAMONDS.getCode(), Cards.ACE_OF_HEARTS.getCode(),
                Cards.ACE_OF_CLUBS.getCode(), Cards.ACE_OF_SPADES.getCode());

        HashMap<String, Object> params = new HashMap<>();
        params.put("cards", specialCardsList);

        log.info("Get Partial Deck");
        RESTResponse<PartialDeckDTO> partialDeck = partialDeckService.getPartialDeckAPI().partialDeck(params);
        assertEquals(partialDeck.getStatus(), HttpStatus.OK);

        String deckId = partialDeck.getResponseBean().getDeckId();
        params.clear();
        params.put("count", specialCardsList.size());

        log.info("Get Draw a Card");
        RESTResponse<DrawCardDTO> drawCard = drawCardService.getDrawCardAPI().drawCard(deckId, params);
        assertEquals(drawCard.getStatus(), HttpStatus.OK);
        assertEquals(drawCard.getResponseBean().getCards().size(), specialCardsList.size(), "Invalid Number of cards in the deck");

        log.info("Validate that the deck contains only specified cards");
        List<String> actualCardList = new ArrayList<>();
        drawCard.getResponseBean().getCards().forEach(card -> actualCardList.add(card.getCode()));
        Collections.sort(actualCardList);
        Collections.sort(specialCardsList);
        assertEquals(specialCardsList, actualCardList, "The Deck contains cards that were not specified");
    }
}