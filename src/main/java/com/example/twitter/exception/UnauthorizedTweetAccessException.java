package com.example.twitter.exception;

public class UnauthorizedTweetAccessException extends RuntimeException {
    public UnauthorizedTweetAccessException(String message) {
        super(message);
    }
}
