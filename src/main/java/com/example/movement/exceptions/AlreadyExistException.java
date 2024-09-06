/* (C) 2023 Cencopay */
package com.example.movement.exceptions;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
