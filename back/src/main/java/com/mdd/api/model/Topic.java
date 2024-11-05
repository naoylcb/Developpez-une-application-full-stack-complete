package com.mdd.api.model;

import java.time.LocalDateTime;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * Entity class representing a Topic in the database.
 */
@Data
@Entity
@Table(name = "TOPICS")
public class Topic {

    /**
     * Unique identifier for the topic.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique name of the topic.
     */
    @Column(unique = true)
    private String name;

    /**
     * Description of the topic.
     */
    private String description;

    /**
     * Timestamp of when the topic was created.
     * Automatically set by the system.
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Timestamp of when the topic was last updated.
     * Automatically updated by the system.
     */
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}