@smoke
Feature: Create Brand New Deck

  Scenario: The user can create a brand new deck
    Given user creates brand new deck
    Then verify that deck contains 52 cards by default