package com.challenge.tenpo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class Addition {
    
    private Long id;
    private Double firstAddend;
    private Double secondAddend;
    private Integer percentage;
    private Double result;
}
