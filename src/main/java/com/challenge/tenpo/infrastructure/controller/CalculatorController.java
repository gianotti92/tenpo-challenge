package com.challenge.tenpo.infrastructure.controller;

import com.challenge.tenpo.application.service.AdditionService;
import com.challenge.tenpo.infrastructure.controller.dto.AdditionDto;
import com.challenge.tenpo.infrastructure.controller.dto.ExternalCallDto;
import com.challenge.tenpo.infrastructure.controller.mapper.AdditionControllerMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/adding")
public class CalculatorController {

  private AdditionService additionService;

  private AdditionControllerMapper additionControllerMapper;

  public CalculatorController(AdditionService additionService, AdditionControllerMapper additionControllerMapper) {
    this.additionService = additionService;
    this.additionControllerMapper = additionControllerMapper;
  }

  @PostMapping
  public ResponseEntity<AdditionDto> create(@RequestBody AdditionDto additionDto) throws JsonProcessingException {
    var addition = additionControllerMapper.map(additionDto);
    additionService.calculateAddition(addition);
    return ResponseEntity.ok().build();
  }

  @GetMapping(params = { "page", "size" })
  public ResponseEntity<List<ExternalCallDto>> get(@RequestParam("page") Integer page,
                                                   @RequestParam("size") Integer size) {
    var response = additionService.getAdditions(page, size).stream()
            .map(additionControllerMapper::map)
            .collect(Collectors.toList());
    
    return ResponseEntity.ok(response);
  }

}
