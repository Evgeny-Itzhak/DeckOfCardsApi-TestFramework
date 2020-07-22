package com.deckofcardsapi.utils.enums;

public enum Cards {

    ACE_OF_DIAMONDS("AD"),
    ACE_OF_HEARTS("AH"),
    ACE_OF_CLUBS("AC"),
    ACE_OF_SPADES("AS");

    private final String card;

    Cards(String card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return card;
    }
}