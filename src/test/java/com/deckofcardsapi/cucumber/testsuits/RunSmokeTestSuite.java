package com.deckofcardsapi.cucumber.testsuits;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(glue = "com.deckofcardsapi.cucumber.steps",
        features = "src/test/resources/cucumber/features/",
        tags = "@smoke",
        plugin = {"pretty", "html:target/cucumber/cucumber-html-report", "json:target/cucumber/cucumber-json-report.json"},
        monochrome = true
)
public class RunSmokeTestSuite extends AbstractTestNGCucumberTests {
}