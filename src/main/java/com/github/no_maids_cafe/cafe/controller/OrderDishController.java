package com.github.no_maids_cafe.cafe.controller;

import com.github.no_maids_cafe.cafe.entity.OrderDish;
import com.github.no_maids_cafe.cafe.service.OrderDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orderdish")
public class OrderDishController {
    @Autowired
    private OrderDishService orderdishService;

    @GetMapping("")
    public @ResponseBody Iterable<OrderDish> list() {
        return orderdishService.list();
    }

    @PostMapping("/update")
    public @ResponseBody String update(@RequestBody OrderDish orderdish) {
        return orderdishService.update(orderdish);
    }

    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestBody OrderDish orderdish) {
        return orderdishService.delete(orderdish);
    }
}
