package com.challenge.tenpo.infrastructure.persistence.mapper;

import com.challenge.tenpo.domain.model.ExternalCall;
import com.challenge.tenpo.infrastructure.persistence.dto.ExternalCallPersistenceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface ExternalCallPersistenceMapper {
    @Mapping(target = "result", source = "result")
    @Mapping(target = "httpCode", source = "httpCode")
    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    ExternalCallPersistenceDto map(ExternalCall addition);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "result", source = "result")
    @Mapping(target = "httpCode", source = "httpCode")
    @Mapping(target = "created", source = "created")
    ExternalCall map(ExternalCallPersistenceDto dto);
}
