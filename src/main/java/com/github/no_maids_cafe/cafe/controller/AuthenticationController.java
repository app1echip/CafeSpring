package com.github.no_maids_cafe.cafe.controller;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.service.UserRoleService;
import com.github.no_maids_cafe.cafe.service.UserService;
import com.github.no_maids_cafe.cafe.util.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController {
    @Autowired
    private UserService service;
    @Autowired
    private UserRoleService role;

    @PostMapping("/pub/authenticate")
    public @ResponseBody ResponseEntity<?> authenticate(@RequestBody User user) {
        if (service.authenticate(user.getUsername(), user.getPassword())) {
            String username = user.getUsername();
            String type = role.getUserRole(service.getIdByUsername(username));
            return ResponseEntity.ok(Token.generate(username, type));
        }
        return new ResponseEntity<String>("Username and password mismatch", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/pub/register")
    public @ResponseBody ResponseEntity<?> register(@RequestBody User user) {
        if (service.add(user)) {
            String username = user.getUsername();
            String type = role.getUserRole(service.getIdByUsername(username));
            return ResponseEntity.ok(Token.generate(username, type));
        }
        return new ResponseEntity<String>("Username already token", HttpStatus.CONFLICT);
    }
}