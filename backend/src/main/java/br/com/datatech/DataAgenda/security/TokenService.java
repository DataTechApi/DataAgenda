package br.com.datatech.DataAgenda.security;

import br.com.datatech.DataAgenda.entity.Tecnico;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("    ${jwt.secret}")
    private String secret;

    public String gerarToken(Tecnico tecnico) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("DataAgenda")
                    .withSubject(tecnico.getEmail())
                    .withClaim("role", tecnico.getRole().getRole())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algoritmo);
            return token;

        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token: " + e.getMessage());
        }

    }

    public String validarToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            String email = JWT.require(algoritmo)
                    .withIssuer("DataAgenda")
                    .build()
                    .verify(token)
                    .getSubject();
            return email;

        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
