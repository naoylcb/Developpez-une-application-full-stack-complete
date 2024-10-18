package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mdd.api.dto.AppUserDto;
import com.mdd.api.dto.TopicDto;

import lombok.Data;

@Data
public class PostDto {

    @NotNull
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotNull
    private AppUserDto author;

    @NotNull
    private TopicDto topic;
}
