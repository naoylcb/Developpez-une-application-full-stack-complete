package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * DTO for AppUser entities.
 * Used for transferring user data between layers.
 */
@Data
public class AppUserDto {

    /**
     * The unique identifier of the user.
     * Cannot be null.
     */
    @NotNull
    private Long id;

    /**
     * The username of the user.
     * Cannot be empty.
     */
    @NotEmpty
    private String username;

    /**
     * The email address of the user.
     * Cannot be empty.
     */
    @NotEmpty
    private String email;
}
