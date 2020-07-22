package com.deckofcardsapi.utils.properties.interfaces;

import org.aeonbits.owner.*;

@Config.Sources({"classpath:base.url.properties"})
public interface Environment extends Config {

    @DefaultValue("TEST")
    String environment();

    @Key("BASE.URL.${environment}")
    String baseUrl();
}