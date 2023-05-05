package com.challenge.tenpo.infrastructure.persistence.mapper;

import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.infrastructure.persistence.dto.AdditionPersistenceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper()
public interface AdditionPersistenceMapper {
    @Mapping(target = "firstAddend", source = "firstAddend")
    @Mapping(target = "secondAddend", source = "secondAddend")
    @Mapping(target = "percentage", source = "percentage")
    @Mapping(target = "result", source = "result")
    AdditionPersistenceDto map(Addition addition);

    @Mapping(target = "firstAddend", source = "firstAddend")
    @Mapping(target = "secondAddend", source = "secondAddend")
    @Mapping(target = "percentage", source = "percentage")
    @Mapping(target = "result", source = "result")
    @Mapping(target = "id", source = "id")
    Addition map(AdditionPersistenceDto dto);
}
