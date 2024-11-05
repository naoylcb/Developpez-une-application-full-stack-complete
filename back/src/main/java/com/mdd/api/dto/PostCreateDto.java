package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

import lombok.Data;

/**
 * DTO for creating a new post.
 * Contains the required fields for post creation.
 */
@Data
public class PostCreateDto {

    /**
     * The title of the post.
     * Cannot be empty.
     */
    @NotEmpty
    private String title;

    /**
     * The content of the post.
     * Cannot be empty.
     */
    @NotEmpty
    private String content;

    /**
     * The ID of the topic this post belongs to.
     * Cannot be null and must be 0 or greater.
     */
    @NotNull
    @Min(0)
    private Long topicId;
}
