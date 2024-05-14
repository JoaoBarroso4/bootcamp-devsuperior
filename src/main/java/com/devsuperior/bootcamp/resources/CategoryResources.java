package com.devsuperior.bootcamp.resources;

import com.devsuperior.bootcamp.entities.Category;
import com.devsuperior.bootcamp.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResources {

    private final CategoryService service;

    public CategoryResources(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = service.findAll();
        return ResponseEntity.ok().body(categories);
    }
}
