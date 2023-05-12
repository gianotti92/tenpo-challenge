package com.challenge.tenpo.infrastructure.persistence.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Schema(name = "tenpodb")
@Table(name = "external_call")
public class ExternalCallPersistenceDto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "result", nullable = false, columnDefinition = "varchar(200)")
    private String result;
    @Column(name = "http_code", nullable = false, columnDefinition = "int")
    private Integer httpCode;

    @Column(name = "created", nullable = false, columnDefinition = "date")
    private LocalDateTime created;
    


}
