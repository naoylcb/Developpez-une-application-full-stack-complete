package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateCommentDto {

    @NotEmpty
    private String content;

    @NotNull
    private Long postId;
}
