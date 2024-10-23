package com.pantao_st_crm.exception;

public class CustomEntityNotFoundException extends RuntimeException {
    public CustomEntityNotFoundException(String message) {
        super("Entity not found: " + message);
    }
}
