package com.github.no_maids_cafe.cafe.controller.raw;

import com.github.no_maids_cafe.cafe.entity.OrdreFood;
import com.github.no_maids_cafe.cafe.service.OrdreFoodService;
import com.github.no_maids_cafe.cafe.util.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrdreFoodController {
    @Autowired
    private OrdreFoodService service;

    @GetMapping("/admin/ordre_food")
    public @ResponseBody Iterable<OrdreFood> list() {
        return service.list();
    }

    @PostMapping("/admin/ordre_food/update")
    public @ResponseBody ResponseEntity<?> update(@RequestBody OrdreFood entity) {
        return Query.response(service::update, entity);
    }

    @PostMapping("/admin/ordre_food/delete")
    public @ResponseBody ResponseEntity<?> delete(@RequestBody OrdreFood entity) {
        return Query.response(service::delete, entity);
    }
}