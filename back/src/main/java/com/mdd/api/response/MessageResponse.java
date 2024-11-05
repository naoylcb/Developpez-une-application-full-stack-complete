package com.mdd.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response object containing a simple message.
 */
@Data
@AllArgsConstructor
public class MessageResponse {
    /**
     * The message content of the response.
     */
    private String message;
}
