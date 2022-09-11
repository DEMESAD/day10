package com.rest.springbootemployee.exception;

public class NoEmployeeFoundException extends RuntimeException {
    public NoEmployeeFoundException() {
        super("No employee found");
    }
}
