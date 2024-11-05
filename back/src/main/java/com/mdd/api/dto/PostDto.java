package com.mdd.api.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mdd.api.dto.AppUserDto;
import com.mdd.api.dto.TopicDto;

import lombok.Data;

/**
 * DTO for Post entities.
 * Used for transferring post data between layers.
 */
@Data
public class PostDto {

    /**
     * The unique identifier of the post.
     * Cannot be null.
     */
    @NotNull
    private Long id;

    /**
     * The title of the post.
     * Cannot be empty.
     */
    @NotEmpty
    private String title;

    /**
     * The main content of the post.
     * Cannot be empty.
     */
    @NotEmpty
    private String content;

    /**
     * The user who authored the post.
     * Cannot be null.
     */
    @NotNull
    private AppUserDto author;

    /**
     * The topic this post belongs to.
     * Cannot be null.
     */
    @NotNull
    private TopicDto topic;

    /**
     * The timestamp when this post was created.
     * Cannot be null.
     */
    @NotNull
    private LocalDateTime createdAt;
}
