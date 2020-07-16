package com.github.no_maids_cafe.cafe.controller;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.service.UserService;
import com.github.no_maids_cafe.cafe.util.TokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(path = "/auth")
    public @ResponseBody ResponseEntity<?> login(@RequestBody User user) {
        boolean match = userService.authenticate(user.getUsername(), user.getPassword());
        if (match) {
            String token = tokenUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.ok("failure");
    }
}