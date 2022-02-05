/*
package com.cjss.CartService.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    @Value("${app.secret}")
    private String secret;

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer("DEVARAJU")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(20)))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public Claims getCl



    aims(String token)  {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token).getBody();
    }

    public String getUserName(String token) {
        return getClaims(token).getSubject();
    }
}
*/
