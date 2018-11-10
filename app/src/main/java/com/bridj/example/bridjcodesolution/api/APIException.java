package com.bridj.example.bridjcodesolution.api;

public class APIException extends Exception {
    public APIException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIException(String message) {
        super(message);
    }
}
