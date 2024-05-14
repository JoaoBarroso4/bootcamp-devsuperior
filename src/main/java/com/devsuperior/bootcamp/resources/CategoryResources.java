package com.devsuperior.bootcamp.resources;

import com.devsuperior.bootcamp.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResources {

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Books"));
        return ResponseEntity.ok().body(categories);
    }
}
