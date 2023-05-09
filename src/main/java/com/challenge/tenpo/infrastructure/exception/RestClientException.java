package com.challenge.tenpo.infrastructure.exception;

public class RestClientException extends RuntimeException{

    public RestClientException(String message) {
        super(message);
    }
}
