package br.com.rappidu.infra.exceptions;

public class InvalidProductException extends RuntimeException {

    public InvalidProductException(String message) {
        super(message);
    }
}
