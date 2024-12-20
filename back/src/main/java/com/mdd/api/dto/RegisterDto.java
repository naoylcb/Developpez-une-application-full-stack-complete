package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * DTO for registering a new user.
 * Contains the required fields for user registration.
 */
@Data
public class RegisterDto {

    /**
     * The username for the new user.
     * Cannot be empty.
     */
    @NotEmpty
    private String username;

    /**
     * The email address for the new user.
     * Cannot be empty.
     */
    @NotEmpty
    private String email;

    /**
     * The password for the new user.
     * Must be at least 8 characters long and contain:
     * - At least one digit
     * - At least one lowercase letter
     * - At least one uppercase letter
     * - At least one special character
     */
    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8,}$", message = "Le mot de passe doit comporter au moins 8 caractères et doit contenir au moins un chiffre, une lettre minuscule, une lettre majuscule et un caractère spécial.")
    private String password;

}
