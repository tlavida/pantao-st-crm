package com.pantao_st_crm.exception;

public class CustomEntityNotFoundException extends RuntimeException {
    public CustomEntityNotFoundException(Class<?> entityClass) {
        super("Entity " +  entityClass.getSimpleName() + " not found");
    }
}
