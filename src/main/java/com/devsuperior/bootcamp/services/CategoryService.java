package com.devsuperior.bootcamp.services;

import com.devsuperior.bootcamp.dto.CategoryDTO;
import com.devsuperior.bootcamp.entities.Category;
import com.devsuperior.bootcamp.repositories.CategoryRepository;
import com.devsuperior.bootcamp.services.exceptions.DatabaseException;
import com.devsuperior.bootcamp.services.exceptions.EntityAlreadyExists;
import com.devsuperior.bootcamp.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> categories = repository.findAll(pageable);

        // Convert the list of Category to a list of CategoryDTO
        return categories.map(CategoryDTO::new);
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Category category = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Entity not found")
        );

        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        if(repository.existsByName(dto.getName())) {
            throw new EntityAlreadyExists("Entity already exists");
        }

        Category category = new Category();
        category.setName(dto.getName());
        category = repository.save(category);
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category category = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id not found " + id)
        );
        category.setName(dto.getName());
        category = repository.save(category);
        return new CategoryDTO(category);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
