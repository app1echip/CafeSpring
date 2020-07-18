package com.github.no_maids_cafe.cafe.controller.raw;

import com.github.no_maids_cafe.cafe.entity.OrdreFood;
import com.github.no_maids_cafe.cafe.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/orderfood")
public class OrderFoodController {
    @Autowired
    private OrderFoodService orderfoodService;

    @GetMapping("")
    public @ResponseBody Iterable<OrdreFood> list() {
        return orderfoodService.list();
    }

    @PostMapping("/update")
    public @ResponseBody String update(@RequestBody OrdreFood orderfood) {
        return orderfoodService.update(orderfood);
    }

    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestBody OrdreFood orderfood) {
        return orderfoodService.delete(orderfood);
    }
}
