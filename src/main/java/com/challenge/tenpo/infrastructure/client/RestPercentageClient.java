package com.challenge.tenpo.infrastructure.client;

import com.challenge.tenpo.domain.client.PercentageClient;
import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.domain.model.ExternalCall;
import com.challenge.tenpo.infrastructure.exception.RestClientException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;


@Component
public class RestPercentageClient implements PercentageClient {
    
    private String baseUrl;
    private static final String URL = "/calculate-percentage?a=%s&b=%s";
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    
    private Map<Double, PercentageRestResponseDto> lastResult;

    public RestPercentageClient(@Value("${rest-client.base-url}") String baseUrl,
                                RestTemplate restTemplate,
                                ObjectMapper objectMapper,
                                Map<Double, PercentageRestResponseDto> lastResult) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
        this.objectMapper =objectMapper;
        this.lastResult = lastResult;
    }

    @Retryable
    @Override
    public ExternalCall getExternalCall(Addition addition) {
        return Optional.ofNullable(lastResult.get(addition.getFirstAddend() + addition.getSecondAddend()))
                .map(this::getCachedResult).orElseGet(() -> this.getFromExternalClient(addition));
    }
    
    
    @SneakyThrows
    private ExternalCall getCachedResult(PercentageRestResponseDto percentageRestResponseDto) {
        return ExternalCall.builder()
                .result(objectMapper.writeValueAsString(percentageRestResponseDto.getResult()).replace("\\", ""))
                .httpCode(percentageRestResponseDto.getStatus())
                .build();
    }

    @SneakyThrows
    private ExternalCall getFromExternalClient(Addition addition) {
        var url = String.format(StringUtils.join(baseUrl, URL), addition.getFirstAddend(), addition.getSecondAddend());
        ResponseEntity<PercentageRestResponseDto> response = null;
       
        try{
            response = restTemplate.exchange(url, HttpMethod.GET, null,
                    PercentageRestResponseDto.class);
        }catch (Exception e) {

            var percentageRestResponseDto = PercentageRestResponseDto.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .result(objectMapper.writeValueAsString(e.getMessage()))
                    .build();
            
            return ExternalCall.builder()
                    .result(objectMapper.writeValueAsString(percentageRestResponseDto))
                    .httpCode(percentageRestResponseDto.getStatus())
                    .build();
       
        }

        if (!response.getStatusCode().is2xxSuccessful()) {

            var percentageRestResponseDto = PercentageRestResponseDto.builder()
                    .status(response.getStatusCode().value())
                    .result(objectMapper.writeValueAsString(new RestClientException(String.format("Error while consuming percentage client, for status code %s",
                            response.getStatusCode())).getMessage().substring(0, 99)))
                    .build();

            lastResult.put(addition.getFirstAddend() + addition.getSecondAddend(), percentageRestResponseDto);

            return ExternalCall.builder()
                    .result(objectMapper.writeValueAsString(percentageRestResponseDto))
                    .httpCode(response.getStatusCode().value())
                    .build();
        } else {

            var percentageRestResponseDto = PercentageRestResponseDto.builder()
                    .status(response.getStatusCode().value())
                    .result(objectMapper.writeValueAsString(response.getBody()))
                    .build();
    
            lastResult.put(addition.getFirstAddend() + addition.getSecondAddend(), percentageRestResponseDto);
                
            return ExternalCall.builder()
                    .result("\"" + objectMapper.writeValueAsString(response.getBody())+ "\"")
                    .httpCode(response.getStatusCode().value())
                    .build();
        
        }

    }
}
