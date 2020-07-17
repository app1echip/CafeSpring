package com.github.no_maids_cafe.cafe.controller;

import java.security.Principal;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public @ResponseBody User get(Principal principal) {
        return userService.findByUsername(principal.getName());
    }

    @PostMapping(path = "/update")
    public @ResponseBody ResponseEntity<?> update(User user, Principal principal) {
        user.setId(userService.getId(principal.getName()));
        String result = userService.update(user);
        if (result.equals("success"))
            return ResponseEntity.ok(result);
        else
            return new ResponseEntity<String>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}