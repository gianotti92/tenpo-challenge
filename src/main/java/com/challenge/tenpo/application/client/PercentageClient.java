package com.challenge.tenpo.application.client;

import com.challenge.tenpo.domain.model.Addition;

public interface PercentageClient {

    /**
     * This method will return a percentage using the values of addition
     * @param addition of class {@link Addition}
     * @return Integer
     */
    Integer obtainPercentage(Addition addition);
}
