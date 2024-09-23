package com.northcoders.RecordShopAPI.exception;

public class MissingFieldValueException extends RuntimeException {

    public MissingFieldValueException(String message) {
        super(message);
    }
}
