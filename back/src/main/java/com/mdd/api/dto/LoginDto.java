package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * DTO for user login.
 * Contains the credentials required for user authentication.
 */
@Data
public class LoginDto {

    /**
     * The email address of the user attempting to login.
     * Cannot be empty.
     */
    @NotEmpty
    private String email;

    /**
     * The password of the user attempting to login.
     * Cannot be empty.
     */
    @NotEmpty
    private String password;
}
