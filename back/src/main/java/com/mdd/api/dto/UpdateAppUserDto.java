package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * DTO for updating an existing AppUser.
 * Contains the fields that can be updated for a user.
 */
@Data
public class UpdateAppUserDto {

    /**
     * The new username for the user.
     * Cannot be empty.
     */
    @NotEmpty
    private String username;

    /**
     * The new email address for the user.
     * Cannot be empty.
     */
    @NotEmpty
    private String email;

    /**
     * The new password for the user.
     * Must be at least 8 characters long and contain:
     * - At least one digit
     * - At least one lowercase letter
     * - At least one uppercase letter
     * - At least one special character
     * If null, the password will not be updated.
     */
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8,}$", message = "Le mot de passe doit comporter au moins 8 caractères et doit contenir au moins un chiffre, une lettre minuscule, une lettre majuscule et un caractère spécial.")
    private String password = null;

}
