package com.challenge.tenpo.domain.service;

import com.challenge.tenpo.domain.model.ExternalCall;
import com.challenge.tenpo.domain.repository.ExternalCallRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionDomainService {
    
    private ExternalCallRepository additionRepository;

    public AdditionDomainService(ExternalCallRepository additionRepository) {
        this.additionRepository = additionRepository;
    }
    
    public ExternalCall save(ExternalCall externalCall) {
        return additionRepository.save(externalCall);
    }
    
    public List<ExternalCall> findAll(Integer page, Integer size) {
        return additionRepository.findAllByPage(Pageable.ofSize(size).withPage(page));
    }

}
