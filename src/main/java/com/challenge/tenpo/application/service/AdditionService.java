package com.challenge.tenpo.application.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import com.challenge.tenpo.application.client.PercentageClient;
import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.domain.repository.AdditionRepository;
import com.challenge.tenpo.domain.service.AdditionDomainService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AdditionService {

  private PercentageClient percentageClient;

  private AdditionDomainService additionDomainService;

  private AdditionRepository additionRepository;

  public AdditionService(PercentageClient percentageClient, AdditionDomainService additionDomainService,
      AdditionRepository additionRepository) {
    this.percentageClient = percentageClient;
    this.additionDomainService = additionDomainService;
    this.additionRepository = additionRepository;
  }

  @Transactional
  public Addition calculateAddition(Addition addition) {
    var percentage = percentageClient.obtainPercentage(addition);
    var result = additionDomainService.calculateAddition(addition, percentage);
    this.asyncSave(result);
    return result;
  }

  private void asyncSave(Addition addition) {
    var additionPersisted = additionRepository.save(addition);
    CompletableFuture.completedFuture(additionPersisted).join();
  }
}
