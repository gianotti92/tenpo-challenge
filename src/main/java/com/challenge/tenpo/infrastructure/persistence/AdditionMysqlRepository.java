package com.challenge.tenpo.infrastructure.persistence;

import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.domain.repository.AdditionRepository;
import com.challenge.tenpo.infrastructure.persistence.mapper.AdditionPersistenceMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AdditionMysqlRepository implements AdditionRepository {

    private AdditionJPARepository additionJPARepository;
    private AdditionPersistenceMapper additionPersistenceMapper;

    public AdditionMysqlRepository(AdditionJPARepository additionJPARepository, AdditionPersistenceMapper additionPersistenceMapper) {
        this.additionJPARepository = additionJPARepository;
        this.additionPersistenceMapper = additionPersistenceMapper;
    }

    @Override
    public Addition save(Addition addition) {
        var additionToBePersisted = additionPersistenceMapper.map(addition);
        var additionPersisted = additionJPARepository.save(additionToBePersisted);
        return additionPersistenceMapper.map(additionPersisted);
    }
}
