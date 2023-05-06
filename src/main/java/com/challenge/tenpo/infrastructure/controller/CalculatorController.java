package com.challenge.tenpo.infrastructure.controller;

import com.challenge.tenpo.application.service.AdditionService;
import com.challenge.tenpo.infrastructure.controller.dto.AdditionDto;
import com.challenge.tenpo.infrastructure.controller.mapper.AdditionControllerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

  private AdditionService additionService;

  private AdditionControllerMapper additionControllerMapper;

  public CalculatorController(AdditionService additionService, AdditionControllerMapper additionControllerMapper) {
    this.additionService = additionService;
    this.additionControllerMapper = additionControllerMapper;
  }

  @PostMapping("/v1/adding")
  public ResponseEntity<AdditionDto> create(@RequestBody AdditionDto additionDto) {
    var addition = additionControllerMapper.map(additionDto);
    additionControllerMapper.map(additionService.calculateAddition(addition));
    return ResponseEntity.ok().build();
  }

}
