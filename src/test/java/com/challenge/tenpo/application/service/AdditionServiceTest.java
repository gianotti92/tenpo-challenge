package com.challenge.tenpo.application.service;

import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.domain.service.AdditionDomainService;
import com.challenge.tenpo.infraestructure.client.PercentageClient;
import com.challenge.tenpo.infraestructure.service.AdditionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdditionServiceTest {

    private static final Integer SOME_VALID_PERCENTAGE = 10;
    @InjectMocks
    private AdditionService additionService;
    @Mock
    private PercentageClient percentageClient;
    @Mock
    private AdditionDomainService additionDomainService;
    
    @Mock
    private Addition addition;
    

    @Test
    public void given_valid_addition_and_valid_percentage_when_calculate_then_response_is_ok() {
        when(percentageClient.obtainPercentage(addition)).thenReturn(SOME_VALID_PERCENTAGE);
        when(additionDomainService.calculateAddition(addition, SOME_VALID_PERCENTAGE)).thenReturn(addition);

        additionService.calculateAddition(addition);

        verify(percentageClient).obtainPercentage(addition);
        verify(additionDomainService).calculateAddition(addition, SOME_VALID_PERCENTAGE);
        verifyNoMoreInteractions(percentageClient);
        verifyNoMoreInteractions(additionDomainService);
        
    }
    
    
}