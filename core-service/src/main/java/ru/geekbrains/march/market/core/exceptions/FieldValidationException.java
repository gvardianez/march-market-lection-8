package ru.geekbrains.march.market.core.exceptions;

public class FieldValidationException extends RuntimeException {
    public FieldValidationException(String message) {
        super(message);
    }
}
