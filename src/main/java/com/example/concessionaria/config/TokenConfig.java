package com.example.concessionaria.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.concessionaria.model.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
public class TokenConfig {

    private final String secret = "mindgoblin";

    public String generateToken(User user) {
        // Mantido o ajuste anterior (Role singular)
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("userId", user.getId())
                .withClaim("roles", List.of(user.getRole().getName().name()))
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decode = JWT.require(algorithm)
                    .build().verify(token);

            // Adicionamos a lógica para extrair a lista de roles com segurança
            List<String> roles = decode.getClaim("roles") != null
                    ? decode.getClaim("roles").asList(String.class)
                    : List.of(); // Se for null, retorna List.of() (lista vazia)

            // Se o roles for nulo após a extração, tratamos para evitar NPE
            if (roles == null) {
                roles = List.of();
            }

            return Optional.of(JWTUserData.builder()
                    .userId(decode.getClaim("userId").asLong())
                    .email(decode.getSubject())
                    .roles(roles) // Passando a lista não nula
                    .build());
        }
        catch (JWTVerificationException ex) {
            // Se o token for inválido, retornamos Optional.empty()
            return Optional.empty();
        }
    }
}