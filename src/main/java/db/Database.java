package db;


import domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Database {

    void add(Product product);

    Optional<Product> findProductByTitle (String title);

    boolean remove(Product product);

    List<Product> getProductList();

    void addCustomCategory(String categoryTitle);

    Map<Integer, String> getCategories();
}
