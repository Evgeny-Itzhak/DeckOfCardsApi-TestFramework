package com.deckofcardsapi.utils.properties;

import com.deckofcardsapi.utils.properties.interfaces.Environment;
import org.aeonbits.owner.ConfigFactory;

import java.util.HashMap;
import java.util.Map;

public class PropertyManager {

    /**
     * Create a Config object to work with BASE_URL.
     *
     * @return instance of base_url properties
     */

    public Environment getEnvironmentConfiguration() {
        return ConfigFactory.create(Environment.class, setEnvironment());

    }

    private Map<String, String> setEnvironment() {

        Map<String, String> executionEnvironment = new HashMap<>();
        String environment = System.getProperty("environment");

        if (environment == null) {
            environment = "TEST";
        }

        environment = environment.toUpperCase();
        executionEnvironment.put("environment", environment);

        return executionEnvironment;
    }
}