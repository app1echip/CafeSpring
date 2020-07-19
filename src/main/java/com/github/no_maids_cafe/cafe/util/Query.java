package com.github.no_maids_cafe.cafe.util;

import java.util.function.Consumer;
import java.util.function.Function;
import org.springframework.http.ResponseEntity;

import lombok.experimental.UtilityClass;

import org.springframework.http.HttpStatus;

@UtilityClass
public class Query {
    public <T> ResponseEntity<?> response(Consumer<T> operation, T entity) {
        try {
            operation.accept(entity);
        } catch (Exception exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok("Succeed");
    }
}