package com.deckofcardsapi;

import com.deckofcardsapi.utils.helpers.DeckHelper;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class BrandNewDeckTest {

    private DeckHelper deckHelper = new DeckHelper();

    @Test(description = "[A Brand New Deck] Open a brand new deck of cards")
    public void getBrandNewDeck() {
        deckHelper.getBrandNewDeck();
    }
}