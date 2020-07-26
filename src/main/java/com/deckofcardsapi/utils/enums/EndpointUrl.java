package com.deckofcardsapi.utils.enums;

public enum EndpointUrl {

    NEW("new/"),
    NEW_SHUFFLE("new/shuffle/"),
    NEW_DRAW("new/draw/"),
    DRAW("draw/"),
    PILE("pile/"),
    ADD("add/"),
    DRAW_BOTTOM("draw/bottom"),
    LIST("list");


    private final String url;

    EndpointUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}