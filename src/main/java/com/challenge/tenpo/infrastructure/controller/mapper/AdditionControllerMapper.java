package com.challenge.tenpo.infrastructure.controller.mapper;

import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.infrastructure.controller.dto.AdditionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AdditionControllerMapper {
    @Mapping(target = "firstAddend", source = "firstAddend")
    @Mapping(target = "secondAddend", source = "secondAddend")
    @Mapping(target = "percentage", source = "percentage")
    @Mapping(target = "result", source = "result")
    AdditionDto map(Addition addition);
    
    @Mapping(target = "firstAddend", source = "firstAddend")
    @Mapping(target = "secondAddend", source = "secondAddend")
    @Mapping(target = "percentage", source = "percentage")
    @Mapping(target = "result", source = "result")
    @Mapping(target = "id", source = "id")
    Addition map(AdditionDto dto);
}
