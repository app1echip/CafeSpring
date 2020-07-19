package com.github.no_maids_cafe.cafe.controller.raw;

import com.github.no_maids_cafe.cafe.entity.Ordre;
import com.github.no_maids_cafe.cafe.service.OrdreService;
import com.github.no_maids_cafe.cafe.util.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrdreController {
    @Autowired
    private OrdreService service;

    @GetMapping("/admin/ordre")
    public @ResponseBody Iterable<Ordre> list() {
        return service.list();
    }

    @PostMapping("/admin/ordre/update")
    public @ResponseBody ResponseEntity<?> update(@RequestBody Ordre entity) {
        return Query.response(service::update, entity);
    }

    @DeleteMapping("/admin/ordre/delete")
    public @ResponseBody ResponseEntity<?> delete(@RequestBody Ordre entity) {
        return Query.response(service::delete, entity);
    }
}