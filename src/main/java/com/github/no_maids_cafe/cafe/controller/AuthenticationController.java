package com.github.no_maids_cafe.cafe.controller;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.service.UserService;
import com.github.no_maids_cafe.cafe.util.TokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping(path = "/authenticate")
    public @ResponseBody ResponseEntity<?> login(@RequestBody User user) {
        boolean match = userService.authenticate(user.getUsername(), user.getPassword());
        if (match) {
            String token = tokenUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        }
        return new ResponseEntity<String>("Username and password mismatch", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(path = "/register")
    public @ResponseBody ResponseEntity<?> siginup(@RequestBody User user) {
        boolean good = userService.add(user);
        if (good) {
            String token = tokenUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        }
        return new ResponseEntity<String>("Username already exits", HttpStatus.CONFLICT);
    }
}