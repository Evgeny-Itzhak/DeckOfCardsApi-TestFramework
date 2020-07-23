package com.deckofcardsapi.dto;

import com.deckofcardsapi.dto.auxiliary.CardDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrawCardDTO extends BaseDTO {

    @JsonProperty("cards")
    private List<CardDTO> cards;

    public List<CardDTO> getCards() {
        return cards;
    }
}