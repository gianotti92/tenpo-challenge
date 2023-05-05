package com.challenge.tenpo.infrastructure.persistence;

import com.challenge.tenpo.infrastructure.persistence.dto.AdditionPersistenceDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionJPARepository extends JpaRepository<AdditionPersistenceDto, Long> {
}
