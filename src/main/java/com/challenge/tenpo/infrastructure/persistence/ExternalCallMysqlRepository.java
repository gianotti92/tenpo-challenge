package com.challenge.tenpo.infrastructure.persistence;

import com.challenge.tenpo.domain.model.ExternalCall;
import com.challenge.tenpo.domain.repository.ExternalCallRepository;
import com.challenge.tenpo.infrastructure.persistence.mapper.ExternalCallPersistenceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class ExternalCallMysqlRepository implements ExternalCallRepository {

    private ExternalCallJPARepository additionJPARepository;
    private ExternalCallPersistenceMapper additionPersistenceMapper;

    public ExternalCallMysqlRepository(ExternalCallJPARepository additionJPARepository, ExternalCallPersistenceMapper additionPersistenceMapper) {
        this.additionJPARepository = additionJPARepository;
        this.additionPersistenceMapper = additionPersistenceMapper;
    }

    @Override
    public ExternalCall save(ExternalCall externalCall) {
        var externalCallToBePersisted = additionPersistenceMapper.map(externalCall);
        var externalCallPersisted = additionJPARepository.save(externalCallToBePersisted);
        return additionPersistenceMapper.map(externalCallPersisted);
    }

    @Override
    public List<ExternalCall> findAllByPage(Pageable pageable) {
        return additionJPARepository.findAll(pageable).stream()
                .map(additionPersistenceMapper::map)
                .collect(Collectors.toList());
    }
}
