package com.mdd.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a request contains invalid parameters or data.
 * Returns HTTP 400 Bad Request status code.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /**
     * Constructs a new BadRequestException with the specified error message.
     *
     * @param message The error message describing why the request was invalid
     */
    public BadRequestException(String message) {
        super(message);
    }
}
