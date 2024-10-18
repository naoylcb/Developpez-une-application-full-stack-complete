package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AppUserDto {

    @NotNull
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;
}
