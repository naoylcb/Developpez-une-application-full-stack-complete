package com.mdd.api.response;

import com.mdd.api.dto.AppUserDto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response object containing session information.
 */
@Data
@AllArgsConstructor
public class SessionResponse {

    /**
     * The JWT token used for authentication in subsequent requests.
     */
    private String token;

    /**
     * The authenticated user's information.
     */
    private AppUserDto user;
}
