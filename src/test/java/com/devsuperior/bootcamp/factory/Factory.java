package com.devsuperior.bootcamp.factory;

import com.devsuperior.bootcamp.dto.ProductDTO;
import com.devsuperior.bootcamp.entities.Category;
import com.devsuperior.bootcamp.entities.Product;

import java.time.Instant;
import java.util.List;

public class Factory {

    public static Product createProduct() {
        Product product = new Product(1L, "Phone", "Good Phone", 800.0,
                "https://img.com/img.png", Instant.parse("2021-10-20T03:00:00Z"));
        product.getCategories().add(new Category(2L, "Electronics"));
        return product;
    }

    public static List<Product> createProducts() {
        return List.of(
                new Product(2L, "Phone", "Good Phone", 800.0,
                        "https://img.com/img.png", Instant.parse("2021-10-20T03:00:00Z")),
                new Product(3L, "TV", "Good TV", 1800.0,
                        "https://img.com/img.png", Instant.parse("2021-10-20T03:00:00Z")),
                new Product(4L, "Tablet", "Good Tablet", 1200.0,
                        "https://img.com/img.png", Instant.parse("2021-10-20T03:00:00Z"))
        );
    }

    public static ProductDTO createProductDTO() {
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }
}
