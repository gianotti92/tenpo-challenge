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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestPercentageClientTest {
    
    private RestPercentageClient restPercentageClient;
    
    private static final String MOCK_URL = "some-url";
    
    @Mock
    private RestTemplate restTemplate;
    
    @Mock
    private Addition addition;
    
    @BeforeEach
    public void init() {
        restPercentageClient = new RestPercentageClient(MOCK_URL, restTemplate, new ObjectMapper());
    }
    
    @Test
    public void given_valid_addition_existing_in_cache_when_get_calculation_the_response_will_be_ok() {
        var dto = PercentageRestResponseDto.builder().result("some-result").status(200).build();
        
        ResponseEntity<PercentageRestResponseDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
        
        when(restTemplate.exchange(MOCK_URL + "/calculate-percentage?a=0.0&b=0.0", HttpMethod.GET, null, PercentageRestResponseDto.class))
                .thenReturn(response);
        
        var result = restPercentageClient.getExternalCall(addition, 11d);

        Assertions.assertEquals("{\"result\":\"some-result\",\"status\":200}", result.getResult());
        Assertions.assertEquals(200, result.getHttpCode());
    }
    
}
