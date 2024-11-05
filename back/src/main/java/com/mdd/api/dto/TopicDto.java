package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * DTO for Topic entities.
 * Used for transferring topic data between layers.
 */
@Data
public class TopicDto {

    /**
     * The unique identifier of the topic.
     * Cannot be null.
     */
    @NotNull
    private Long id;

    /**
     * The name of the topic.
     * Cannot be empty.
     */
    @NotEmpty
    private String name;

    /**
     * The description of the topic.
     * Cannot be empty.
     */
    @NotEmpty
    private String description;
}
