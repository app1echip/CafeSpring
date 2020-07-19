package com.github.no_maids_cafe.cafe.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Token implements Serializable {
    private final long validity = 7200;
    private final String secret = "bishojo-cafe";

    private <T> T parse(String token, Function<Claims, T> resolver) {
        return resolver.apply(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody());
    }

    public String getUser(String token){
        return parse(token, Claims::getSubject);
    }

    public boolean expired(String token){
        return parse(token, Claims::getExpiration).before(new Date());
    }

    public String generate(String subject) {
        return Jwts.builder()
            .setClaims(new HashMap<>())
            .setSubject(subject)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + validity * 1000))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }
}