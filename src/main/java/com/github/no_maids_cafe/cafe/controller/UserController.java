package com.github.no_maids_cafe.cafe.controller;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public @ResponseBody Iterable<User> list() {
        return userService.list();
    }

    @PostMapping("/update")
    public @ResponseBody String update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestBody User user) {
        return userService.delete(user);
    }
}
