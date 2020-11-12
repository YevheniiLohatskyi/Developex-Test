package com.developex.testtask.exception;

public class UrlConnectFailedException extends Exception {

    public UrlConnectFailedException() {
        super("Error connecting to url");
    }
}
