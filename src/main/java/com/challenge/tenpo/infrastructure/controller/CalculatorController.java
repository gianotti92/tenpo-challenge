package com.challenge.tenpo.infrastructure.controller;

import com.challenge.tenpo.application.service.AdditionService;
import com.challenge.tenpo.infrastructure.controller.dto.AdditionDto;
import com.challenge.tenpo.infrastructure.controller.dto.ExternalCallDto;
import com.challenge.tenpo.infrastructure.controller.mapper.AdditionControllerMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1/addition")
public class CalculatorController {

  private AdditionService additionService;

  private AdditionControllerMapper additionControllerMapper;

  private final Bucket bucket;
  
  public CalculatorController(AdditionService additionService, AdditionControllerMapper additionControllerMapper) {
    this.additionService = additionService;
    this.additionControllerMapper = additionControllerMapper;

    Refill refill = Refill.intervally(3, Duration.ofMinutes(1));
    Bandwidth limit = Bandwidth.classic(3, refill);
    this.bucket = Bucket.builder()
            .addLimit(limit)
            .build();
  }

  @PostMapping
  public ResponseEntity<AdditionDto> create(@RequestBody AdditionDto additionDto) throws JsonProcessingException {
    if (bucket.tryConsume(1)) {
      log.info("Starting CalculatorController.create with request: {}", additionDto);
      var addition = additionControllerMapper.map(additionDto);
      additionService.calculateAddition(addition);
      return ResponseEntity.ok().build();
    }else {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
    
  }

  @GetMapping(params = { "page", "size" })
  public ResponseEntity<List<ExternalCallDto>> get(@RequestParam("page") Integer page,
                                                   @RequestParam("size") Integer size) {
    log.info("Starting CalculatorController.get with page: {} and size: {}", page, size);
    if (bucket.tryConsume(1)) {
      var response = additionService.getAdditions(page, size).stream()
              .map(additionControllerMapper::map)
              .collect(Collectors.toList());

      return ResponseEntity.ok(response);
    }else {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
    
  }

}
