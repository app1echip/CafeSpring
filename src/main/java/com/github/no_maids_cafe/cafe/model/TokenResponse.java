package com.github.no_maids_cafe.cafe.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenResponse {
    private @Getter String token;
    private @Getter Date expire;
    private @Getter String role;

    public TokenResponse(String token, Date expire, String role) {
        this.token = token;
        this.expire = expire;
        this.role = role;
    }
}