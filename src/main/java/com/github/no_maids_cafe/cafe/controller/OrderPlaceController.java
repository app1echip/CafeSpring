package com.github.no_maids_cafe.cafe.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.github.no_maids_cafe.cafe.service.OrderService;
import com.github.no_maids_cafe.cafe.service.UserService;
import com.github.no_maids_cafe.cafe.model.OrderModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/order")
public class OrderPlaceController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public @ResponseBody List<OrderModel> get(Principal principal) {
        return orderService.getContentByUser(userService.getId(principal.getName()));
    }

    @PostMapping(path = "/new")
    public @ResponseBody ResponseEntity<?> newOrder(@RequestBody Map<String, Integer> content, Principal principal) {
        String user = userService.getId(principal.getName());
        String order = orderService.generate(content, user);
        if (order == null || order.length() != 36) {
            return new ResponseEntity<String>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(order);
    }
}