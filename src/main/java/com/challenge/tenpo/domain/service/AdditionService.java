package com.challenge.tenpo.domain.service;

import com.challenge.tenpo.domain.client.PercentageClient;
import com.challenge.tenpo.domain.model.Addition;
import org.springframework.stereotype.Service;

@Service
public class AdditionService {

    public static final int ONE_HUNDRED = 100;
    private PercentageClient percentageClient;

    public AdditionService(PercentageClient percentageClient) {
        this.percentageClient = percentageClient;
    }

    public Addition calculateAddition(Addition addition) {
        var percentage = percentageClient.obtainPercentage(addition);
        
        return Addition.builder()
                .result(calculateResult(addition, percentage))
                .build();
        
    }

    private Double calculateResult(Addition addition, Integer percentage) {
        var resultOfAddition = addition.getFirstAddend() + addition.getSecondAddend();
        var resultOfPercentage = (Double.valueOf(percentage) / ONE_HUNDRED) * resultOfAddition;
        return resultOfPercentage + resultOfAddition;
    }

}
