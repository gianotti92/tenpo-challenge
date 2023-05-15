package com.challenge.tenpo.application.service;

import com.challenge.tenpo.domain.client.PercentageClient;
import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.domain.model.ExternalCall;
import com.challenge.tenpo.domain.service.AdditionDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public void calculateAddition(Addition addition) {
    var externalCall = percentageClient.getExternalCall(addition, addition.getFirstAddend() + addition.getSecondAddend());
    this.asyncSave(externalCall);
  }

  @Transactional(readOnly = true)
  public List<ExternalCall> getAdditions(Integer page, Integer size) {
    return additionDomainService.findAll(page, size);
  }

  private void asyncSave(ExternalCall externalCall) {
    var additionPersisted = additionDomainService.save(externalCall);
    CompletableFuture.completedFuture(additionPersisted).join();
  }
}
