package com.challenge.tenpo.domain.service;

import static org.mockito.Mockito.when;

import com.challenge.tenpo.domain.client.PercentageClient;
import com.challenge.tenpo.domain.model.Addition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdditionServiceTest {
  
  @Mock
  private Addition addition;
  
  @Mock
  private PercentageClient percentageClient;
  
  @InjectMocks
  private AdditionService additionService;
  
  @Test
  public void given_valid_addition_when_calculate_then_response_is_ok() {
    when(addition.getFirstAddend()).thenReturn(5.0);
    when(addition.getSecondAddend()).thenReturn(5.0);
    when(percentageClient.obtainPercentage(addition)).thenReturn(10);

    var result = additionService.calculateAddition(addition);

    Assertions.assertEquals(11, result.getResult());
  }
  
}
