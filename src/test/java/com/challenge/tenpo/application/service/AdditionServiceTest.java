package com.challenge.tenpo.application.service;

import com.challenge.tenpo.domain.client.PercentageClient;
import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.domain.model.ExternalCall;
import com.challenge.tenpo.domain.service.AdditionDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdditionServiceTest {
    
    @InjectMocks
    private AdditionService additionService;
    @Mock
    private PercentageClient percentageClient;
    @Mock
    private AdditionDomainService additionDomainService;
    
    @Mock
    private ExternalCall mockExternalCall;
    
    @Mock
    private Addition mockAddition;
    

    @Test
    public void given_valid_addition_when_calculate_then_addition_domain_service_will_be_called_ok() {
        when(percentageClient.getExternalCall(mockAddition, anyDouble())).thenReturn(mockExternalCall);
        when(additionDomainService.save(mockExternalCall)).thenReturn(mockExternalCall);
        
        additionService.calculateAddition(mockAddition);
        
        verify(additionDomainService).save(mockExternalCall);
        verifyNoMoreInteractions(additionDomainService);
    }
}
