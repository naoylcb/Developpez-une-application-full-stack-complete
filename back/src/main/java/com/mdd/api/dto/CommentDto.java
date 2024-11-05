package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

import com.mdd.api.dto.AppUserDto;

/**
 * DTO for Comment entities.
 * Used for transferring comment data between layers.
 */
@Data
public class CommentDto {

    /**
     * The unique identifier of the comment.
     * Cannot be null.
     */
    @NotNull
    private Long id;

    /**
     * The content of the comment.
     * Cannot be empty.
     */
    @NotEmpty
    private String content;

    /**
     * The user who authored the comment.
     * Cannot be null.
     */
    @NotNull
    private AppUserDto author;

}
