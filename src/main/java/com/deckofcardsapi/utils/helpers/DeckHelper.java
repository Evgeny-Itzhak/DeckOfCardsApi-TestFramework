package com.deckofcardsapi.utils.helpers;

import com.deckofcardsapi.dto.BrandNewDeckDTO;
import com.deckofcardsapi.dto.DrawCardDTO;
import com.deckofcardsapi.dto.PartialDeckDTO;
import com.deckofcardsapi.services.BrandNewDeckService;
import com.deckofcardsapi.services.DrawCardService;
import com.deckofcardsapi.services.PartialDeckService;
import com.deckofcardsapi.services.response.RESTResponse;
import com.deckofcardsapi.utils.enums.HttpStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Slf4j
public class DeckHelper {

    private BrandNewDeckService brandNewDeckService = new BrandNewDeckService();
    private DrawCardService drawCardService = new DrawCardService();
    private PartialDeckService partialDeckService = new PartialDeckService();

    public BrandNewDeckDTO getBrandNewDeck() {
        log.info("Get Brand New Deck");
        RESTResponse<BrandNewDeckDTO> response = brandNewDeckService.getBrandNewDeckAPI().brandNewDeck();
        assertEquals(response.getStatus(), HttpStatus.OK);
        return response.getResponseBean();
    }

    public DrawCardDTO getDrawCardFromNewDeck(HashMap<String, Object> params) {
        log.info("Get Draw a Card from the new deck request");
        RESTResponse<DrawCardDTO> drawCard = drawCardService.getDrawCardAPI().drawCard(params);
        assertEquals(drawCard.getStatus(), HttpStatus.OK);
        return drawCard.getResponseBean();
    }

    public List<String> getCardsFromDrawCardFromExistingDeck(DrawCardDTO drawCardDTO) {
        log.info("Get Drawn Cards from the new deck request");
        List<String> drawnCards = new ArrayList<>();
        drawCardDTO.getCards().forEach(card -> drawnCards.add(card.getCode()));
        return drawnCards;
    }

    public PartialDeckDTO getPartialDeck(HashMap<String, Object> params) {
        log.info("Get Partial Deck");
        RESTResponse<PartialDeckDTO> partialDeck = partialDeckService.getPartialDeckAPI().partialDeck(params);
        assertEquals(partialDeck.getStatus(), HttpStatus.OK);
        return partialDeck.getResponseBean();
    }
}
