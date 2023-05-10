package com.challenge.tenpo.infrastructure.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PercentageRestResponseDto {
    private String result;
    
    private Integer status;
}
