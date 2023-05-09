package com.challenge.tenpo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ExternalCall {
    private Long id;
    private String result;
    private Integer httpCode;
    private LocalDateTime created;
}
