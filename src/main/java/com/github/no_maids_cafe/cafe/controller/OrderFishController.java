package com.github.no_maids_cafe.cafe.controller;

import com.github.no_maids_cafe.cafe.entity.OrderFood;
import com.github.no_maids_cafe.cafe.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orderfood")
public class OrderFishController {
    @Autowired
    private OrderFoodService orderfoodService;

    @GetMapping("")
    public @ResponseBody Iterable<OrderFood> list() {
        return orderfoodService.list();
    }

    @PostMapping("/update")
    public @ResponseBody String update(@RequestBody OrderFood orderfood) {
        return orderfoodService.update(orderfood);
    }

    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestBody OrderFood orderfood) {
        return orderfoodService.delete(orderfood);
    }
}
