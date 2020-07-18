package com.github.no_maids_cafe.cafe.controller.raw;

import com.github.no_maids_cafe.cafe.entity.UserRole;
import com.github.no_maids_cafe.cafe.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/role")
public class UserRoleController {
    @Autowired
    private UserRoleService UserRoleService;

    @GetMapping("")
    public @ResponseBody Iterable<UserRole> list() {
        return UserRoleService.list();
    }

    @PostMapping("/update")
    public @ResponseBody String update(@RequestBody UserRole UserRole) {
        return UserRoleService.update(UserRole);
    }
}
