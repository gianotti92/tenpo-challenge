package com.challenge.tenpo.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExternalCallDto {
    private Long id;
    private String result;
    private Integer httpCode;
    private LocalDateTime created;
}
