package com.deckofcardsapi.utils.properties;

public class BaseProperties {

    private static PropertyManager propertyManager = new PropertyManager();

    public static String baseUrl = propertyManager.getEnvironmentConfiguration().baseUrl();
}