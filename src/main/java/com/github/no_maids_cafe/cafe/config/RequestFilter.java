package com.github.no_maids_cafe.cafe.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.service.UserService;
import com.github.no_maids_cafe.cafe.util.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String username = null;
        if (token != null)
            try {
                username = Token.getUser(token);
            } catch (Exception exception) {
            }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = service.findByUsername(username);
            if (user != null && !Token.expired(token)) {
                UserDetails detail = service.getDetail(user);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(detail, null,
                        detail.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}