package org.example.fooddelivery.exceptions;

public class OperationNotSupportedException extends RuntimeException {
    public OperationNotSupportedException(String message) {
        super(message);
    }
}
