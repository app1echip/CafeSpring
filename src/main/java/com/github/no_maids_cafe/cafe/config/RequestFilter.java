package com.github.no_maids_cafe.cafe.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.service.UserRoleService;
import com.github.no_maids_cafe.cafe.service.UserService;
import com.github.no_maids_cafe.cafe.util.TokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestFilter extends OncePerRequestFilter {
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String username = null;
        String token = null;
        String start = "Cafe ";
        if (header != null && header.startsWith(start)) {
            token = header.substring(start.length());
            try {
                username = tokenUtil.getUsername(token);
            } catch (Exception e) {
                // do something
            }
        } else {
            // do something
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.findByUsername(username);
            if (user != null && tokenUtil.validate(token, username)) {
                String password = user.getPassword();
                String role = userRoleService.getRole(user.getId());
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(role));
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, password,
                        authorities);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}