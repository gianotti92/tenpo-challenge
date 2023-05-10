package com.challenge.tenpo.domain.service;

import com.challenge.tenpo.domain.model.ExternalCall;
import com.challenge.tenpo.domain.repository.ExternalCallRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdditionDomainServiceTest {
  
  @Mock
  private ExternalCall mockExternalCall;
  @Mock
  private Pageable page;
  @Mock
  private ExternalCallRepository externalCallRepository;
  
  @InjectMocks
  private AdditionDomainService additionDomainService;
  @Captor
  private ArgumentCaptor<Pageable> pageableArgumentCaptor = ArgumentCaptor.forClass(Pageable.class);
  
  @Test
  public void given_valid_external_call_when_save_then_external_call_will_be_ok() {
    when(externalCallRepository.save(mockExternalCall)).thenReturn(mockExternalCall);

    additionDomainService.save(mockExternalCall);

    verify(externalCallRepository).save(mockExternalCall);
    verifyNoMoreInteractions(externalCallRepository.save(mockExternalCall));
  }

  @Test
  public void given_valid_page_and_size_when_find_all_then_list_of_external_call_will_will_be_returned_ok() {
    when(externalCallRepository.findAllByPage(pageableArgumentCaptor.capture())).thenReturn(List.of(mockExternalCall));

    var result = additionDomainService.findAll(0, 100);

    Assertions.assertEquals(1, result.size());
    Assertions.assertEquals(100, pageableArgumentCaptor.getValue().getPageSize());
  }
  
}
