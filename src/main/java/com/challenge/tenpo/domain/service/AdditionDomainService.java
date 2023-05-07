package com.challenge.tenpo.domain.service;

import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.domain.repository.AdditionRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionDomainService {

    public static final int ONE_HUNDRED = 100;
    
    private AdditionRepository additionRepository;

    public AdditionDomainService(AdditionRepository additionRepository) {
        this.additionRepository = additionRepository;
    }

    public Addition calculateAddition(Addition addition, Integer percentage) {
        return addition.toBuilder()
                .percentage(percentage)
                .result(calculateResult(addition, percentage))
                .build();
    }
    
    public Addition save(Addition addition) {
        return additionRepository.save(addition);
    }
    
    public List<Addition> findAll(Integer page, Integer size) {
        return additionRepository.findAllByPage(Pageable.ofSize(size).withPage(page));
    }

    private Double calculateResult(Addition addition, Integer percentage) {
        var resultOfAddition = addition.getFirstAddend() + addition.getSecondAddend();
        var resultOfPercentage = (Double.valueOf(percentage) / ONE_HUNDRED) * resultOfAddition;
        return resultOfPercentage + resultOfAddition;
    }

}
