package com.northcoders.RecordShopAPI.exception;

public class DuplicateEntryException extends RuntimeException {

    public DuplicateEntryException(String message) {
        super(message);
    }
}
