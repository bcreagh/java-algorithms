package com.bcreagh.javaalgos.exceptions;

public class DuplicateKeyException extends RuntimeException {

    public DuplicateKeyException(String key) {
        super("The provided key already exists. The key provided was: " + key);
    }

}
