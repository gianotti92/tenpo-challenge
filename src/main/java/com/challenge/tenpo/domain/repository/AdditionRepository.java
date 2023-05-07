package com.challenge.tenpo.domain.repository;

import com.challenge.tenpo.domain.model.Addition;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdditionRepository {
  
  Addition save(Addition addition);
  
  List<Addition> findAllByPage(Pageable pageable);

}
