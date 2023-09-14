package io.renatofreire.poc_redis.exception;

public class InvalidBodyException extends Throwable {
    public InvalidBodyException(String message) {
        super(message);
    }
}
