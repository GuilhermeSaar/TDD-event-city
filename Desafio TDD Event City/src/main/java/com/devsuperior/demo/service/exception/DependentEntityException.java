package com.devsuperior.demo.service.exception;

public class DependentEntityException extends RuntimeException {

    public DependentEntityException(String message) {
        super(message);
    }
}
