package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

import com.mdd.api.dto.AppUserDto;

@Data
public class CommentDto {

    @NotNull
    private Long id;

    @NotEmpty
    private String content;

    @NotNull
    private AppUserDto author;

}
