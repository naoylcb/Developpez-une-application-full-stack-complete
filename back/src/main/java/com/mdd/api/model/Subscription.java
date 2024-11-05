package com.mdd.api.model;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Entity class representing a Subscription in the database.
 */
@Data
@Entity
@Table(name = "SUBSCRIPTIONS")
public class Subscription {

    /**
     * Unique identifier for the subscription.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who created the subscription.
     */
    @ManyToOne
    @JoinColumn(name = "user")
    private AppUser user;

    /**
     * The topic that the user is subscribed to.
     */
    @ManyToOne
    @JoinColumn(name = "topic")
    private Topic topic;

    /**
     * Timestamp of when the subscription was created.
     * Automatically set by the system.
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Timestamp of when the subscription was last updated.
     * Automatically updated by the system.
     */
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}