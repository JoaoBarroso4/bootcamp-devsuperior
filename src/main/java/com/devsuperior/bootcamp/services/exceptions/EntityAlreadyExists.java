package com.devsuperior.bootcamp.services.exceptions;

public class EntityAlreadyExists extends RuntimeException {

    public EntityAlreadyExists(String msg) {
        super(msg);
    }
}
