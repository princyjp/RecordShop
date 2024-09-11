package com.northcoders.RecordShopAPI.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String message) {
        super(message);
    }
}
