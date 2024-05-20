package com.devsuperior.bootcamp.repositories;

import com.devsuperior.bootcamp.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest // This annotation is used to test JPA repositories
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long nonExistingId;

    @BeforeEach
    public void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;

//        Product product = new Product();
//        product.setId(10L);
//        product.setName("Phone");
//        product.setDescription("Good phone");
//        product.setPrice(800.0);
//        product.setImgUrl("https://img.com/img.png");
//        repository.save(product);
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        repository.deleteById(existingId);

        Optional<Product> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }
}
