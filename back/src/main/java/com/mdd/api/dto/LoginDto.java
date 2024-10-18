package com.mdd.api.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
