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

    @GetMapping("/list")
    public @ResponseBody
    Iterable<User> list() {
        return userService.list();
    }

    @PostMapping("/update")
    public @ResponseBody
    String update(@RequestParam String username,
                  @RequestParam String password,
                  @RequestParam String email,
                  @RequestParam String phone) {
        User user = userService.findByUsername(username);
        if (user == null) user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        userService.update(user);
        return "Done";
    }

    @PostMapping("/delete")
    public @ResponseBody
    String delete(@RequestParam String id) {
        userService.deleteById(id);
        return "Done";
    }
}
