package com.devsuperior.bootcamp.repositories;

import com.devsuperior.bootcamp.entities.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    default void deleteById(@NonNull Long id) {
        Optional<Product> entity = findById(id);
        if (entity.isPresent()) {
            delete(entity.get());
        } else {
            throw new NoSuchElementException("Entity not found");
        }
    }
}
