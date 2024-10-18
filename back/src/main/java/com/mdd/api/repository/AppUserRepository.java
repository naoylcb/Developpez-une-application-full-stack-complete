package com.mdd.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdd.api.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public Optional<AppUser> findByEmail(String email);
}