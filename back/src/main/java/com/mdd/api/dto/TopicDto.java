package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TopicDto {

    @NotNull
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;
}
