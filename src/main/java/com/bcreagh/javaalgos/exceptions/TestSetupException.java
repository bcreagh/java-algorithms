package com.bcreagh.javaalgos.exceptions;

public class TestSetupException extends RuntimeException {

    public TestSetupException(Throwable e) {
        super("There was an exception while setting up a test", e);
    }
}
