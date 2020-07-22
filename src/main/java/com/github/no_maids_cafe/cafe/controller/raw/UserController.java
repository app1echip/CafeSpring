package com.github.no_maids_cafe.cafe.controller.raw;

import com.github.no_maids_cafe.cafe.entity.User;
import com.github.no_maids_cafe.cafe.service.UserService;
import com.github.no_maids_cafe.cafe.util.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/admin/user")
    public @ResponseBody Iterable<User> list() {
        return service.list();
    }

    @PostMapping("/admin/user/update")
    public @ResponseBody ResponseEntity<?> update(@RequestBody User entity) {
        return Query.response(service::update, entity);
    }

    @PostMapping("/admin/user/delete")
    public @ResponseBody ResponseEntity<?> delete(@RequestBody User entity) {
        return Query.response(service::delete, entity);
    }
}