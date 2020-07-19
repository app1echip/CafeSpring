package com.github.no_maids_cafe.cafe.controller;

import java.security.Principal;

import com.github.no_maids_cafe.cafe.service.OrdreService;
import com.github.no_maids_cafe.cafe.service.UserService;
import com.github.no_maids_cafe.cafe.model.OrderDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
    @Autowired
    private OrdreService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/api/order")
    public @ResponseBody Iterable<OrderDetails> get(Principal principal) {
        return orderService.getDetailsByUserId(userService.getIdByUsername(principal.getName()));
    }

    @PostMapping("/api/order/new")
    public @ResponseBody ResponseEntity<?> put(@RequestBody Iterable<OrderDetails.Item> items, Principal principal) {
        String user = userService.getIdByUsername(principal.getName());
        String order = orderService.createFromItemsAndUserId(items, user);
        if (order == null || order.length() != 36)
            return new ResponseEntity<String>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.ok(order);
    }
}