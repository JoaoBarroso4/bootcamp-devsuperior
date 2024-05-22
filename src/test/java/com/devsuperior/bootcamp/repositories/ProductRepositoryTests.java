package com.devsuperior.bootcamp.repositories;

import com.devsuperior.bootcamp.entities.Product;
import com.devsuperior.bootcamp.factory.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest // This annotation is used to test JPA repositories
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long nonExistingId;
    private long countTotalProducts;

    @BeforeEach
    public void setup() throws Exception {
        List<Product> products = Factory.createProducts();
        repository.saveAll(products);

        existingId = products.get(0).getId();
        countTotalProducts = repository.count();

        nonExistingId = 1000L;
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertTrue(product.getId() > 0);
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        repository.deleteById(existingId);

        Optional<Product> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }

    // deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() test is not necessary if the
    // deleteById(Long id) method from JpaRepository is being used, as it does not throw an exception when the
    // entity with the given id does not exist

    @Test
    public void findByIdShouldReturnProductWhenIdExists() {
        Optional<Product> result = repository.findById(existingId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExist() {
        Optional<Product> result = repository.findById(nonExistingId);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void findAllShouldReturnAllProducts() {
        List<Product> result = repository.findAll();
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countTotalProducts, result.size());
    }

    @Test
    public void updateShouldChangeProductWhenIdExists() {
        Product product = repository.findById(existingId).orElseThrow();
        String oldName = product.getName();
        String newName = "New Name";
        product.setName(newName);

        product = repository.save(product);

        Assertions.assertNotEquals(oldName, product.getName());
        Assertions.assertEquals(newName, product.getName());
    }
}
