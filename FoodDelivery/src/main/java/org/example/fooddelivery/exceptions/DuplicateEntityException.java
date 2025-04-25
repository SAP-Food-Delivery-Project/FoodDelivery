package org.example.fooddelivery.exceptions;

public class DuplicateEntityException extends FoodDeliveryBaseException {

    public DuplicateEntityException(String type, String attribute, String value) {
        super(String.format("%s with %s %s already exists.", type, attribute, value));
    }

    public DuplicateEntityException(String message) {
        super(message);
    }
}
