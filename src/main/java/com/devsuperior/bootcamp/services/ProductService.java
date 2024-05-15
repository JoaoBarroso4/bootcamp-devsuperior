package com.devsuperior.bootcamp.services;

import com.devsuperior.bootcamp.dto.ProductDTO;
import com.devsuperior.bootcamp.entities.Product;
import com.devsuperior.bootcamp.repositories.ProductRepository;
import com.devsuperior.bootcamp.services.exceptions.DatabaseException;
import com.devsuperior.bootcamp.services.exceptions.EntityAlreadyExists;
import com.devsuperior.bootcamp.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // This annotation informs that this method will be a transaction in the database,
    // following ACID principles, and betters performance
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
        Page<Product> categories = repository.findAll(pageRequest);

        // Convert the list of Product to a list of ProductDTO
        return categories.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Entity not found")
        );

        return new ProductDTO(product, product.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        if(repository.existsByName(dto.getName())) {
            throw new EntityAlreadyExists("Entity already exists");
        }

        Product product = new Product();
        //product.setName(dto.getName());
        product = repository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id not found " + id)
        );
        //product.setName(dto.getName());
        product = repository.save(product);
        return new ProductDTO(product);
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
