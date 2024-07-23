package br.com.rappidu.infra.persistence.repositories.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
         super(message);
    }
}
