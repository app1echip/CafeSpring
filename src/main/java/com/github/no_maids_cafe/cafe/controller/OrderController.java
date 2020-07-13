package com.github.no_maids_cafe.cafe.controller;

import com.github.no_maids_cafe.cafe.entity.Order;
import com.github.no_maids_cafe.cafe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public @ResponseBody Iterable<Order> list() {
        return orderService.list();
    }

    @PostMapping("/update")
    public @ResponseBody String update(@RequestBody Order order) {
        return orderService.update(order);
    }

    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestBody Order order) {
        return orderService.delete(order);
    }
}
