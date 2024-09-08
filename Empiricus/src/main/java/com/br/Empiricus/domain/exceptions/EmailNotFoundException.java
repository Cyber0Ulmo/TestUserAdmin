package com.br.Empiricus.domain.exceptions;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String message) {
        super(message);
    }
}
