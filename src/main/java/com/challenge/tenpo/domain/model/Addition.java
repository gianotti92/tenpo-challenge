package com.challenge.tenpo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Addition {
    
    private Double firstAddend;
    private Double secondAddend;
    private Integer percentage;
    private Double result;
}
