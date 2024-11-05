package com.mdd.api.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mdd.api.dto.LoginDto;
import com.mdd.api.dto.RegisterDto;
import com.mdd.api.dto.UpdateAppUserDto;
import com.mdd.api.response.SessionResponse;
import com.mdd.api.service.AppUserService;
import com.mdd.api.exceptions.BadRequestException;
import com.mdd.api.exceptions.NotFoundException;
import com.mdd.api.response.MessageResponse;

/**
 * REST controller for managing user accounts and authentication.
 */
@RestController
public class UserController {

    @Autowired
    private AppUserService appUserService;

    /**
     * Registers a new user account.
     *
     * @param registerDto The registration information for the new user
     * @return ResponseEntity containing either:
     *         - 200 OK with SessionResponse (JWT token and user info) if registration successful
     *         - 400 Bad Request with error message if email or username already exists
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        try {
            SessionResponse response = appUserService.createUser(registerDto);
            return ResponseEntity.ok(response);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    /**
     * Authenticates a user.
     *
     * @param loginDto The login credentials
     * @return ResponseEntity containing either:
     *         - 200 OK with SessionResponse (JWT token and user info) if login successful
     *         - 401 Unauthorized with error message if credentials are invalid
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        try {
            SessionResponse response = appUserService.connectUser(loginDto);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Nom d'utilisateur ou mot de passe incorrect"));
        }
    }

    /**
     * Updates the authenticated user's profile information.
     *
     * @param authentication The authentication object containing user details
     * @param updateAppUserDto The new user information to update
     * @return ResponseEntity containing either:
     *         - 200 OK with SessionResponse (new JWT token and updated user info) if update successful
     *         - 400 Bad Request with error message if new email or username already exists
     *         - 404 Not Found with error message if user not found
     */
    @PutMapping("/me")
    public ResponseEntity<?> updateProfile(Authentication authentication,
            @Valid @RequestBody UpdateAppUserDto updateAppUserDto) {
        try {
            SessionResponse response = appUserService.updateUser(authentication.getName(), updateAppUserDto);
            return ResponseEntity.ok(response);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
        }
    }
}