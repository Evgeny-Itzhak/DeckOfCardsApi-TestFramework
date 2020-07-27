package com.deckofcardsapi.cucumber.steps;

import com.deckofcardsapi.utils.helpers.DeckHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.testng.Assert.assertEquals;

public class BrandNewDeckSteps {

    private final DeckHelper deckHelper = new DeckHelper();

    private String deckId;
    private static final Integer NUMBER_OF_CARDS_IN_THE_DECK_BY_DEFAULT = 52;

    @Given("user creates brand new deck")
    public void userCreatesBrandNewDeck() {
        deckId = deckHelper.getBrandNewDeck().getDeckId();
    }

    @Then("verify that deck contains 52 cards by default")
    public void verify_that_deck_contains_52_cards_by_default() {
        assertEquals(deckHelper.getDeckInfo(deckId).getRemaining(), NUMBER_OF_CARDS_IN_THE_DECK_BY_DEFAULT, "Incorrect number of cards in the deck by default");
    }
}