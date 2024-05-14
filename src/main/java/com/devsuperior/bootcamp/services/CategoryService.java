package com.devsuperior.bootcamp.services;

import com.devsuperior.bootcamp.entities.Category;
import com.devsuperior.bootcamp.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    // This annotation informs that this method will be a transaction in the database,
    // following ACID principles, and betters performance
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return repository.findAll();
    }
}
