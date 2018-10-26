package db;


import domain.Product;

import java.util.List;
import java.util.Optional;

public interface Database {

    void add(Product product);

    Optional<Product> findProductByTitle (String title);

    boolean remove(Product product);

    List<Product> getProductList();
}
