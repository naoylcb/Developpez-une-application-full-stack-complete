package com.mdd.api.service;

import java.time.Instant;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.mdd.api.model.AppUser;
import com.nimbusds.jose.jwk.source.ImmutableSecret;

/**
 * Service class for handling JWT (JSON Web Token) operations.
 */
@Service
public class JWTService {

    @Value("${security.jwt.key}")
    private String jwtKey;

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    /**
     * Generates a JWT token for the given user.
     * The token includes claims for issuer, issuance time, expiration time (24
     * hours from issuance),
     * and the user's email as the subject.
     *
     * @param appUser The user for whom to generate the token
     * @return A JWT token string encoded with HS256 algorithm
     */
    public String generateToken(AppUser appUser) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(24 * 3600))
                .subject(appUser.getEmail())
                .build();

        NimbusJwtEncoder encoder = new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtKey.getBytes()));
        JwtEncoderParameters params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return encoder.encode(params).getTokenValue();
    }
}
