package com.challenge.tenpo.infrastructure.persistence.dto;

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
@Table(name = "addition")
public class AdditionPersistenceDto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_addend")
    private Double firstAddend;
    @Column(name = "second_addend")
    private Double secondAddend;
    @Column(name = "percentage")
    private Integer percentage;
    @Column(name = "result")
    private Double result;
    @Column(name = "created")
    private LocalDateTime created;
}


