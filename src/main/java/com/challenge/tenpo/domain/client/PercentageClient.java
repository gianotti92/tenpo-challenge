package com.challenge.tenpo.domain.client;

import com.challenge.tenpo.domain.model.Addition;
import com.challenge.tenpo.domain.model.ExternalCall;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PercentageClient {
    ExternalCall getExternalCall(Addition addition);
    
}
