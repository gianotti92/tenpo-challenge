package com.challenge.tenpo.infrastructure.client;

import com.challenge.tenpo.domain.client.PercentageClient;
import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.domain.model.ExternalCall;
import com.challenge.tenpo.infrastructure.exception.RestClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestPercentageClient implements PercentageClient {
    
    private String baseUrl;
    private static final String URL = "/calculate-percentage?a=%s&b=%s";
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    public RestPercentageClient(@Value("${rest-client.base-url}") String baseUrl, 
                                RestTemplate restTemplate,
                                ObjectMapper objectMapper) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
        this.objectMapper =objectMapper;
    }

    @Override
    public ExternalCall getExternalCall(Addition addition) throws JsonProcessingException {
        var url = String.format(StringUtils.join(baseUrl, URL), addition.getFirstAddend(), addition.getSecondAddend());
        
        ResponseEntity<PercentageRestResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, null,
                PercentageRestResponseDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RestClientException(String.format("Error while consuming percentage client, for status code %s", response.getStatusCode()));
        }

        return ExternalCall.builder()
                .result(objectMapper.writeValueAsString(response.getBody()))
                .httpCode(response.getStatusCode().value())
                .build();
    }
}
