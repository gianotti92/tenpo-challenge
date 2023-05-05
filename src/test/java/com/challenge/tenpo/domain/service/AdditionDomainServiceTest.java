package com.challenge.tenpo.domain.service;

import com.challenge.tenpo.domain.model.Addition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdditionDomainServiceTest {
  
  private static final Integer PERCENTAGE_TO_CALCULATE = 10;
  
  @Mock
  private Addition addition;
  
  @InjectMocks
  private AdditionDomainService additionDomainService;
  
  @Test
  public void given_valid_addition_when_calculate_then_response_is_ok() {
    when(addition.getFirstAddend()).thenReturn(5.0);
    when(addition.getSecondAddend()).thenReturn(5.0);

    var result = additionDomainService.calculateAddition(addition, PERCENTAGE_TO_CALCULATE);

    Assertions.assertEquals(11, result.getResult());
  }
  
}
