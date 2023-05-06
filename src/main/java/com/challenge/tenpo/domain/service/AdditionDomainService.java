package com.challenge.tenpo.domain.service;

import com.challenge.tenpo.domain.model.Addition;
import org.springframework.stereotype.Service;

@Service
public class AdditionDomainService {

    public static final int ONE_HUNDRED = 100;
    
    public Addition calculateAddition(Addition addition, Integer percentage) {
        return addition.toBuilder()
                .percentage(percentage)
                .result(calculateResult(addition, percentage))
                .build();
    }

    private Double calculateResult(Addition addition, Integer percentage) {
        var resultOfAddition = addition.getFirstAddend() + addition.getSecondAddend();
        var resultOfPercentage = (Double.valueOf(percentage) / ONE_HUNDRED) * resultOfAddition;
        return resultOfPercentage + resultOfAddition;
    }

}
