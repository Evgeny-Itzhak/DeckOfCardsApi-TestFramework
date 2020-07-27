package com.deckofcardsapi;

import com.deckofcardsapi.utils.helpers.DeckHelper;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Epic("TESTING for http://deckofcardsapi.com/ API")
@Feature(value = "Brand New Deck endpoint")
@Story(value = "Create a Brand New Deck of cards")
@Severity(SeverityLevel.CRITICAL)
@Slf4j
public class BrandNewDeckTest {

    private DeckHelper deckHelper = new DeckHelper();

    @Test(description = "[A Brand New Deck] Open a brand new deck of cards")
    @Description("Validate creation of a brand new deck of cards")
    public void getBrandNewDeck() {
        deckHelper.getBrandNewDeck();
    }
}