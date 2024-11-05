package com.mdd.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource is not found.
 * Returns HTTP 404 Not Found status code.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException with the specified error message.
     *
     * @param message The error message describing why the resource was not found
     */
    public NotFoundException(String message) {
        super(message);
    }
}
