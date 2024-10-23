package com.pantao_st_crm.exception;

public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException(Class<?> entityClass) {
        super("Entity " + entityClass.getSimpleName() + " already exists");
    }
}
