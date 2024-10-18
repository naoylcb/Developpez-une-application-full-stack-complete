package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class PostCreateDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotNull
    @Min(0)
    private Long topicId;
}
