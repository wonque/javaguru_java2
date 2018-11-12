package db;

import domain.Product;

import java.util.Optional;

public interface ProductRepositorySearch {

    Optional<Product> findByTitle(String title);
}
