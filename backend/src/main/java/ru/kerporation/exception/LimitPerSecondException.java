package ru.kerporation.exception;

public class LimitPerSecondException extends RuntimeException {
    public LimitPerSecondException(String message) {
        super(message);
    }
}
