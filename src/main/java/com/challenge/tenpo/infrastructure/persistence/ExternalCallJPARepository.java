package com.challenge.tenpo.infrastructure.persistence;

import com.challenge.tenpo.infrastructure.persistence.dto.ExternalCallPersistenceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExternalCallJPARepository extends JpaRepository<ExternalCallPersistenceDto, Long> {
    Page<ExternalCallPersistenceDto> findAll(Pageable pageable);
}
