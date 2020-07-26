package com.deckofcardsapi.services;

import com.deckofcardsapi.services.request.AbstractRequest;
import com.deckofcardsapi.services.response.RESTResponse;
import com.deckofcardsapi.services.response.RESTResponseImpl;
import com.deckofcardsapi.utils.enums.HttpMethod;
import com.deckofcardsapi.utils.enums.HttpStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.StringJoiner;

import static io.restassured.RestAssured.expect;

@Slf4j
public class RESTHandler {

    <T> RESTResponse<T> sendRequest(AbstractRequest request) {

        RequestSpecification finalRequest = RestAssured.given();
        Map<String, String> headers = request.getHeaders();
        Map<String, String> queryParameters = request.getQueryParameters();

        finalRequest.headers(headers);
        finalRequest.queryParams(queryParameters);

        HttpMethod httpMethod = request.getHttpMethod();
        String url = request.getUrl();

        Object requestBody = request.getRequestBody();
        String json = null;
        if (requestBody != null) {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            try {
                json = ow.writeValueAsString(requestBody).replace("\\\\", "\\");
                finalRequest.body(json);
                finalRequest.contentType("application/json; charset=utf-8");
            } catch (JsonProcessingException e) {
                log.error("Exception with processing json", e);
            }
        }

        logRequest(headers, queryParameters, json, httpMethod, url);

        Instant start = Instant.now();
        Response response = httpMethod.sendRequest(url, finalRequest);
        Instant finish = Instant.now();

        logResponse(Duration.between(start, finish), response);

        return new RESTResponseImpl<>(response);
    }

    private void logRequest(Map<String, String> headers, Map<String, String> params, Object requestBody, HttpMethod httpMethod, String url) {
        StringBuilder loggedRequest = new StringBuilder("Calling " + httpMethod.getMethod() + " " + url);
        if (!params.isEmpty()) {
            StringJoiner sj = new StringJoiner("&", "?", "");
            for (String key : params.keySet()) {
                sj.add(key + "=" + StringUtils.replace(params.get(key), " ", "%20"));
            }
            loggedRequest.append(sj.toString());
        }
        loggedRequest.append("\n");
        for (String headerName : headers.keySet()) {
            loggedRequest
                    .append(headerName)
                    .append(": ")
                    .append(headers.get(headerName))
                    .append("\n");
        }
        if (requestBody != null) {
            loggedRequest.append(requestBody);
        }
        log.info(loggedRequest.toString());
    }

    private void logResponse(Duration duration, Response response) {
        StringBuilder loggedResponse = new StringBuilder("Got HTTP " + response.getStatusCode() + " after " + duration.toMillis() + " ms.\n");
        ResponseBody responseBody = response.getBody();
        if (responseBody != null) {
            if (!responseBody.asString().isEmpty()) {
                Object jsonResponse = new JSONTokener(responseBody.asString()).nextValue();
                String formattedJson;
                if (jsonResponse instanceof JSONArray) {
                    formattedJson = ((JSONArray) jsonResponse).toString(4);
                } else if (jsonResponse instanceof JSONObject) {
                    formattedJson = ((JSONObject) jsonResponse).toString(4);
                } else {
                    formattedJson = response.asString();
                }
                loggedResponse.append(formattedJson);
            }
        }
        log.info(String.valueOf(loggedResponse));
    }

    <T> void verifyResponse(RESTResponse<T> response, HttpStatus expectedStatus) {
        expect().statusCode(expectedStatus.getStatusCode()).validate(response.getRestResponse());
    }
}