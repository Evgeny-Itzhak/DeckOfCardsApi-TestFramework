package com.deckofcardsapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class BaseDTO {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("deck_id")
    private String deckId;

    @JsonProperty("remaining")
    private Integer remaining;
}
