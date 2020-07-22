package com.deckofcardsapi.utils.enums;

public enum EndpointUrl {

    NEW_SHUFFLE("new/shuffle/"),
    DRAW("draw/");

    private final String url;

    EndpointUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}