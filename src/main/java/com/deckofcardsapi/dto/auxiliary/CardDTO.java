package com.deckofcardsapi.dto.auxiliary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDTO {

    @JsonProperty("image")
    private String image;

    @JsonProperty("value")
    private String value;

    @JsonProperty("suit")
    private String suit;

    @JsonProperty("code")
    private String code;
}