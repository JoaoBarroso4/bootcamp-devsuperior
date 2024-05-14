package com.devsuperior.bootcamp.services;

import com.devsuperior.bootcamp.entities.Category;
import com.devsuperior.bootcamp.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll() {
        return repository.findAll();
    }
}
