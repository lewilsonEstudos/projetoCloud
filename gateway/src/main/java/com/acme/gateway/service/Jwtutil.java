package com.acme.gateway.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

@Service
public class Jwtutil {
    private String JWT_SERVICE = "super-secret";
    public String decodeToken(String accessToken){
        Algorithm algorithm = Algorithm.HMAC512(JWT_SERVICE);
        accessToken = accessToken.replace("Bearer", "").strip();
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("wilsonsilva@gmail.com")
                .build();
        DecodedJWT verified = verifier.verify(accessToken);
        return verified.getSubject();
    }
}