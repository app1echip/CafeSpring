package com.github.no_maids_cafe.cafe.controller.raw;

import com.github.no_maids_cafe.cafe.entity.Ordre;
import com.github.no_maids_cafe.cafe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public @ResponseBody Iterable<Ordre> list() {
        return orderService.list();
    }

    @PostMapping("/update")
    public @ResponseBody String update(@RequestBody Ordre order) {
        return orderService.update(order);
    }

    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestBody Ordre order) {
        return orderService.delete(order);
    }
}
