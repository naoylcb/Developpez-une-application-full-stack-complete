package com.mdd.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdd.api.model.AppUser;

/**
 * Repository interface for managing AppUser entities.
 * Provides methods to interact with the user data in the database.
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * Finds a user by their email address.
     *
     * @param email The email address to search for
     * @return Optional containing the user if found, empty otherwise
     */
    public Optional<AppUser> findByEmail(String email);

    /**
     * Finds a user by their username.
     *
     * @param username The username to search for
     * @return Optional containing the user if found, empty otherwise
     */
    public Optional<AppUser> findByUsername(String username);
}