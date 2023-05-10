package com.challenge.tenpo.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdditionDto {
    private Long id;
    private Double firstAddend;
    private Double secondAddend;
    private Integer percentage;
    private Double result;
}
