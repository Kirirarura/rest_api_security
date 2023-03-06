package com.epam.esm.exception;

public class NoSuchEntityException extends RuntimeException{

    private Long id;

    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Long id){
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
