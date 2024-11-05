package com.mdd.api.model;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Entity class representing a Post in the database.
 */
@Data
@Entity
@Table(name = "POSTS")
public class Post {

    /**
     * Unique identifier for the post.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title of the post.
     */
    private String title;

    /**
     * Content of the post.
     */
    private String content;

    /**
     * The user who created the post.
     */
    @ManyToOne
    @JoinColumn(name = "author")
    private AppUser author;

    /**
     * The topic that this post belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "topic")
    private Topic topic;

    /**
     * Timestamp of when the post was created.
     * Automatically set by the system.
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Timestamp of when the post was last updated.
     * Automatically updated by the system.
     */
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}