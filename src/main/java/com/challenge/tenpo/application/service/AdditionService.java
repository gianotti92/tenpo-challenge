package com.challenge.tenpo.application.service;

import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.domain.service.AdditionDomainService;
import com.challenge.tenpo.application.client.PercentageClient;
import org.springframework.stereotype.Service;

@Service
public class AdditionService {
    
    private PercentageClient percentageClient;
    private AdditionDomainService additionDomainService;

    public AdditionService(PercentageClient percentageClient, AdditionDomainService additionDomainService) {
        this.percentageClient = percentageClient;
        this.additionDomainService = additionDomainService;
    }

    public Addition calculateAddition(Addition addition) {
        var percentage = percentageClient.obtainPercentage(addition);
        return additionDomainService.calculateAddition(addition, percentage);
    }
}
