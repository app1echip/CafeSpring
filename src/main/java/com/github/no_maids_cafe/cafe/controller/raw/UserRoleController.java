package com.github.no_maids_cafe.cafe.controller.raw;

import com.github.no_maids_cafe.cafe.entity.UserRole;
import com.github.no_maids_cafe.cafe.service.UserRoleService;
import com.github.no_maids_cafe.cafe.util.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRoleController {
    @Autowired
    private UserRoleService service;

    @GetMapping("/admin/user_role")
    public @ResponseBody Iterable<UserRole> listRole() {
        return service.list();
    }

    @PostMapping("/admin/user_role/update")
    public @ResponseBody ResponseEntity<?> update(@RequestBody UserRole entity) {
        return Query.response(service::update, entity);
    }
}