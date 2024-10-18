package com.mdd.api.response;

import com.mdd.api.dto.AppUserDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private String token;
    private AppUserDto user;
}
