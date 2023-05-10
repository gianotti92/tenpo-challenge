package com.challenge.tenpo.infraestructure.client;

import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.infrastructure.client.PercentageRestResponseDto;
import com.challenge.tenpo.infrastructure.client.RestPercentageClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestPercentageClientTest {
    
    private RestPercentageClient restPercentageClient;
    
    private static final String MOCK_URL = "some-url";
    
    private Map<Double, PercentageRestResponseDto> lastResult = new HashMap<>();
    
    @Mock
    private RestTemplate restTemplate;
    
    @Mock
    private Addition addition;
    
    @Mock
    private ResponseEntity<PercentageRestResponseDto> mockResponse;
    
    
    @BeforeEach
    public void init() {
        restPercentageClient = new RestPercentageClient(MOCK_URL, restTemplate, new ObjectMapper(), lastResult);
    }
    
    @Test
    public void given_valid_addition_existing_in_cache_when_get_calculation_the_response_will_be_ok() {
        when(mockResponse.getBody()).thenReturn(PercentageRestResponseDto.builder().status(200).result("some-result").build());
        
        when(mockResponse.getStatusCode()).thenReturn(HttpStatusCode.valueOf(200));
        
        when(restTemplate.exchange(MOCK_URL + "/calculate-percentage?a=0.0&b=0.0", HttpMethod.GET, null, PercentageRestResponseDto.class))
                .thenReturn(mockResponse);
        
        var result = restPercentageClient.getExternalCall(addition);

        Assertions.assertEquals("\"{\"result\":\"some-result\",\"status\":200}\"", result.getResult());
        Assertions.assertEquals(200, result.getHttpCode());
    }
    
}
