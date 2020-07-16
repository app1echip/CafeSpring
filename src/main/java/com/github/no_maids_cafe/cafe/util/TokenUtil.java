package com.github.no_maids_cafe.cafe.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil implements Serializable {
    public static final long VALIDITY = 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsername(String token) {
        return get(token, Claims::getSubject);
    }

    public Date getExpiration(String token) {
        return get(token, Claims::getExpiration);
    }

    public <T> T get(String token, Function<Claims, T> resolver) {
        final Claims claims = getClaims(token);
        return resolver.apply(claims);
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpiration(token);
        return expiration.before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, username);
    }

    private String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validate(String token, String username) {
        final String tokenUsername = getUsername(token);
        return (tokenUsername.equals(username)) && !isTokenExpired(token);
    }

}