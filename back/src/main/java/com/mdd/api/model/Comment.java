package com.mdd.api.model;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Entity class representing a Comment in the database.
 */
@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    /**
     * Unique identifier for the comment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Content of the comment.
     */
    private String content;

    /**
     * The post that this comment belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;

    /**
     * The user who created the comment.
     */
    @ManyToOne
    @JoinColumn(name = "author")
    private AppUser author;

    /**
     * Timestamp of when the comment was created.
     * Automatically set by the system.
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Timestamp of when the comment was last updated.
     * Automatically updated by the system.
     */
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}