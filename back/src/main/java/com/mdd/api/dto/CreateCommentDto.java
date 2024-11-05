package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * DTO for creating a new comment.
 * Contains the required fields for comment creation.
 */
@Data
public class CreateCommentDto {

    /**
     * The content of the comment.
     * Cannot be empty.
     */
    @NotEmpty
    private String content;

    /**
     * The ID of the post this comment belongs to.
     * Cannot be null.
     */
    @NotNull
    private Long postId;
}
