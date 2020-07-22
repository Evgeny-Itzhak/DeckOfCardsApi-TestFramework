package com.deckofcardsapi.services;


import com.deckofcardsapi.utils.enums.EndpointUrl;
import com.deckofcardsapi.utils.enums.HttpMethod;
import com.deckofcardsapi.utils.properties.PropertyManager;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRequest {

    private static final PropertyManager config = new PropertyManager();
    private static final String baseUrl = config.getEnvironmentConfiguration().baseUrl();
    private String url;
    private HttpMethod httpMethod;
    private EndpointUrl endpointUrl;
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> queryParameters = new HashMap<>();
    private Object requestBody;

    protected AbstractRequest(String url) {
        this.httpMethod = HttpMethod.GET;
        this.url = url;
    }

    public AbstractRequest(EndpointUrl endpointUrl, HttpMethod httpMethod, String deckId) {
        this.httpMethod = httpMethod;
        this.endpointUrl = endpointUrl;
        this.url = baseUrl + deckId + endpointUrl.getUrl();
    }

    public AbstractRequest(EndpointUrl endpointUrl, HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        this.endpointUrl = endpointUrl;
        this.url = baseUrl + endpointUrl.getUrl();
    }

    private String getFullRequestUrl(EndpointUrl endpointUrl, String deckId) {
        return baseUrl + deckId + endpointUrl.getUrl();
    }

    protected void addHeader(String headerName, String headerValue) {
        this.headers.put(headerName, headerValue);
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    protected void setQueryParameters(HashMap<String, Object> params) {
        for (Map.Entry<String, Object> parameter : params.entrySet()) {
            String key = parameter.getKey();
            String value = parameter.getValue().toString();
            queryParameters.put(key, value);
        }
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getQueryParameters() {
        return queryParameters;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}