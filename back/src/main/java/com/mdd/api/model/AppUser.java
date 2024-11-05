package com.mdd.api.model;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Entity class representing a User in the database.
 */
@Data
@Entity
@Table(name = "USERS")
public class AppUser {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique username of the user.
     */
    @Column(unique = true)
    private String username;

    /**
     * Unique email address of the user.
     */
    @Column(unique = true)
    private String email;

    /**
     * Password of the user.
     */
    private String password;

    /**
     * Timestamp of when the user was created.
     * Automatically set by the system.
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Timestamp of when the user was last updated.
     * Automatically updated by the system.
     */
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}