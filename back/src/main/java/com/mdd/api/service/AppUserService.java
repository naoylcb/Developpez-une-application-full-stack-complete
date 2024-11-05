package com.mdd.api.service;

import java.util.Optional;
import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mdd.api.dto.LoginDto;
import com.mdd.api.dto.RegisterDto;
import com.mdd.api.dto.UpdateAppUserDto;
import com.mdd.api.dto.AppUserDto;
import com.mdd.api.model.AppUser;
import com.mdd.api.repository.AppUserRepository;
import com.mdd.api.response.SessionResponse;
import com.mdd.api.exceptions.BadRequestException;
import com.mdd.api.exceptions.NotFoundException;

/**
 * Service class for managing application users.
 */
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

    /**
     * Loads a user by their email address for Spring Security authentication.
     *
     * @param email The email address of the user to load
     * @return UserDetails object containing the user's authentication information
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow();
        return new User(appUser.getEmail(), appUser.getPassword(), Collections.emptyList());
    }

    /**
     * Creates a new user.
     *
     * @param registerDto The information about the user to create
     * @return SessionResponse containing JWT token and user information
     * @throws BadRequestException if email or username is already in use
     */
    public SessionResponse createUser(RegisterDto registerDto) throws BadRequestException {
        if (appUserRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new BadRequestException("Email déjà utilisé");
        }

        if (appUserRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            throw new BadRequestException("Nom d'utilisateur déjà utilisé");
        }

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
        return new SessionResponse(jwtToken, newAppUserDto);
    }

    /**
     * Authenticates a user.
     *
     * @param loginDto The credentials to authenticate the user
     * @return SessionResponse containing JWT token and user information
     * @throws UsernameNotFoundException if the user is not found
     */
    public SessionResponse connectUser(LoginDto loginDto) throws UsernameNotFoundException {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        AppUser appUser = appUserRepository.findByEmail(loginDto.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(appUser);
        AppUserDto appUserDto = modelMapper.map(appUser, AppUserDto.class);
        return new SessionResponse(jwtToken, appUserDto);
    }

    /**
     * Retrieves a user by his email address.
     *
     * @param email The email address to search for
     * @return Optional containing the user if found, empty if not found
     */
    public Optional<AppUser> getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    /**
     * Updates user's information.
     *
     * @param email            The email address of the user to update
     * @param updateAppUserDto The new information about the user to update
     * @return SessionResponse containing new JWT token and updated user information
     * @throws NotFoundException   if the user is not found
     * @throws BadRequestException if the new email or username is already in use by
     *                             another user
     */
    public SessionResponse updateUser(String email, UpdateAppUserDto updateAppUserDto) throws NotFoundException, BadRequestException {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));

        if (appUserRepository.findByEmail(updateAppUserDto.getEmail()).isPresent()
                && !appUserRepository.findByEmail(updateAppUserDto.getEmail()).get().getId().equals(appUser.getId())) {
            throw new BadRequestException("Email déjà utilisé");
        }

        if (appUserRepository.findByUsername(updateAppUserDto.getUsername()).isPresent()
                && !appUserRepository.findByUsername(updateAppUserDto.getUsername()).get().getId()
                        .equals(appUser.getId())) {
            throw new BadRequestException("Nom d'utilisateur déjà utilisé");
        }

        appUser.setEmail(updateAppUserDto.getEmail());
        appUser.setUsername(updateAppUserDto.getUsername());
        if (updateAppUserDto.getPassword() != null) {
            appUser.setPassword(new BCryptPasswordEncoder().encode(updateAppUserDto.getPassword()));
        }
        appUserRepository.save(appUser);

        String jwtToken = jwtService.generateToken(appUser);
        AppUserDto appUserDto = modelMapper.map(appUser, AppUserDto.class);
        return new SessionResponse(jwtToken, appUserDto);
    }
}