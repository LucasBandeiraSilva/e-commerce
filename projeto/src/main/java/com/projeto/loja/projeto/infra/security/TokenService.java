package com.projeto.loja.projeto.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.projeto.loja.projeto.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;

    public String generateToken(Usuario usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secretKey);

            String token = JWT.create()
                    .withIssuer("B&L-auth-api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(this.gerarExpiracao())
                    .sign(algoritmo);

            return token;

        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao criar token.");
        }
    }

    private String validarToken(String token){
        Algorithm algoritmo = Algorithm.HMAC256(secretKey);
        return JWT.require(algoritmo)
                .withIssuer("B&L-auth-api")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant gerarExpiracao(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
