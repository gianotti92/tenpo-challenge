package com.challenge.tenpo.domain.repository;

import com.challenge.tenpo.domain.model.ExternalCall;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExternalCallRepository {

  ExternalCall save(ExternalCall externalCall);
  
  List<ExternalCall> findAllByPage(Pageable pageable);

}
