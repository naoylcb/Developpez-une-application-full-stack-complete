package com.mdd.api.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.mdd.api.dto.LoginDto;
import com.mdd.api.dto.RegisterDto;
import com.mdd.api.dto.AppUserDto;
import com.mdd.api.response.UserResponse;
import com.mdd.api.service.AppUserService;

@RestController
public class UserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterDto registerDto) {
        UserResponse response = appUserService.createUser(registerDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginDto loginDto) {
        UserResponse response = appUserService.connectUser(loginDto);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/me")
    public ResponseEntity<AppUserDto> getProfile(Authentication authentication) {
        AppUserDto appUserDto = appUserService.getUserInfoByEmail(authentication.getName());
        return ResponseEntity.ok(appUserDto);
    }

    @PutMapping("/me")
    public ResponseEntity<AppUserDto> updateProfile(Authentication authentication,
            @Valid @RequestBody RegisterDto registerDto) {
        AppUserDto appUserDto = appUserService.updateUser(authentication, registerDto);
        return ResponseEntity.ok(appUserDto);
    }
}