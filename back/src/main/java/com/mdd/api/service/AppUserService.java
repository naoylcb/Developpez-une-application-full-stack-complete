package com.mdd.api.service;

import java.util.Optional;
import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mdd.api.dto.LoginDto;
import com.mdd.api.dto.RegisterDto;
import com.mdd.api.dto.AppUserDto;
import com.mdd.api.model.AppUser;
import com.mdd.api.repository.AppUserRepository;
import com.mdd.api.response.UserResponse;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow();
        return new User(appUser.getEmail(), appUser.getPassword(), Collections.emptyList());
    }

    public UserResponse createUser(RegisterDto registerDto) {
        AppUser newAppUser = new AppUser();
        newAppUser.setEmail(registerDto.getEmail());
        newAppUser.setUsername(registerDto.getUsername());
        newAppUser.setPassword(new BCryptPasswordEncoder().encode(registerDto.getPassword()));
        appUserRepository.save(newAppUser);

        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(registerDto.getEmail(), registerDto.getPassword()));

        String jwtToken = jwtService.generateToken(newAppUser);
        AppUserDto newAppUserDto = modelMapper.map(newAppUser, AppUserDto.class);
        return new UserResponse(jwtToken, newAppUserDto);
    }

    public UserResponse connectUser(LoginDto loginDto) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        AppUser appUser = appUserRepository.findByEmail(loginDto.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(appUser);
        AppUserDto appUserDto = modelMapper.map(appUser, AppUserDto.class); 
        return new UserResponse(jwtToken, appUserDto);
    }

    public Optional<AppUser> getUserById(Long id) {
        return appUserRepository.findById(id);
    }

    public Optional<AppUser> getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public AppUserDto getUserInfoByEmail(String email) {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow();
        AppUserDto appUserDto = modelMapper.map(appUser, AppUserDto.class);
        return appUserDto;
    }

    public AppUserDto getUserInfoById(Long id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow();
        AppUserDto appUserDto = modelMapper.map(appUser, AppUserDto.class);
        return appUserDto;
    }

    public AppUserDto updateUser(Authentication authentication, RegisterDto registerDto) {
        AppUser appUser = appUserRepository.findByEmail(authentication.getName()).orElseThrow();
        appUser.setEmail(registerDto.getEmail());
        appUser.setUsername(registerDto.getUsername());
        appUser.setPassword(new BCryptPasswordEncoder().encode(registerDto.getPassword()));
        appUserRepository.save(appUser);

        return this.getUserInfoById(appUser.getId());
    }
}