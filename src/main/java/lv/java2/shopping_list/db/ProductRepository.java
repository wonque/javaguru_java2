package lv.java2.shopping_list.db;


import lv.java2.shopping_list.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product addToDataBase(Product product);

    Optional<Product> findByTitle(String title);

    Optional<Product> findById(Long id);

    boolean remove(Product product);

    List<Product> getProductList();

//    void addCustomCategory(String categoryTitle);
//
//    Map<Integer, String> getCategories();
}
