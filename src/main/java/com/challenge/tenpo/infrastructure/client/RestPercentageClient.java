package com.challenge.tenpo.infrastructure.client;

import com.challenge.tenpo.domain.client.PercentageClient;
import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.domain.model.ExternalCall;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
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
    this.objectMapper = objectMapper;
  }

  @Override
  @Retryable
  @Cacheable(cacheNames = {"externalcall"}, key = "#additionResult")
  @SneakyThrows
  public ExternalCall getExternalCall(Addition addition, Double additionResult) {
    log.info("External data not exists, start searching for addition: {}", addition);
    var url = String.format(StringUtils.join(baseUrl, URL), addition.getFirstAddend(), addition.getSecondAddend());

    ResponseEntity<PercentageRestResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, null,
        PercentageRestResponseDto.class);

    if (response.getStatusCode().is2xxSuccessful()) {
      log.info("Consuming external http status id {} for request: {}", response.getStatusCode().value(), addition);
      return ExternalCall.builder()
          .result(objectMapper.writeValueAsString(response.getBody()))
          .httpCode(response.getStatusCode().value())
          .build();
    } else {
      log.info("Consuming external http status id {} for request: {}", response.getStatusCode().value(), addition);

      return ExternalCall.builder()
          .result(objectMapper.writeValueAsString(response.getBody()))
          .httpCode(response.getStatusCode().value())
          .build();
    }

  }
}
