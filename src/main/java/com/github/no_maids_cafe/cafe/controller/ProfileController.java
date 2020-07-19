package com.github.no_maids_cafe.cafe.controller;

import java.security.Principal;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.service.UserService;
import com.github.no_maids_cafe.cafe.util.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {
    @Autowired
    private UserService service;

    @GetMapping("/api/profile")
    public @ResponseBody User get(Principal principal) {
        return service.findByUsername(principal.getName());
    }

    @PostMapping("/api/profile/update")
    public @ResponseBody ResponseEntity<?> update(User entity, Principal principal) {
        entity.setId(service.getIdByUsername(principal.getName()));
        return Query.response(service::update, entity);
    }
}