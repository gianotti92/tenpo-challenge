package com.challenge.tenpo.application.service;

import com.challenge.tenpo.application.client.PercentageClient;
import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.domain.service.AdditionDomainService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AdditionService {

  private PercentageClient percentageClient;

  private AdditionDomainService additionDomainService;
  
  public AdditionService(PercentageClient percentageClient,
                         AdditionDomainService additionDomainService) {
    this.percentageClient = percentageClient;
    this.additionDomainService = additionDomainService;
  }

  @Transactional
  public Addition calculateAddition(Addition addition) {
    var percentage = percentageClient.obtainPercentage(addition);
    var result = additionDomainService.calculateAddition(addition, percentage);
    this.asyncSave(result);
    return result;
  }
  
  public List<Addition> getAdditions(Integer page, Integer size) {
    return additionDomainService.findAll(page, size);
  }

  private void asyncSave(Addition addition) {
    var additionPersisted = additionDomainService.save(addition);
    CompletableFuture.completedFuture(additionPersisted).join();
  }
}
