package lv.java2.shopping_list.db;

import lv.java2.shopping_list.domain.Product;

import java.util.*;

public class InMemoryProductRepository  {

    private List<Product> shopList;

    public InMemoryProductRepository() {
        shopList = new ArrayList<>();
    }

//    @Override
    public Product addToDataBase(Product product) {
        shopList.add(product);
        return product;

    }

//    @Override
    public Optional<Product> findByTitle(String title) {
        return shopList.stream().filter(product -> product.getTitle().equals(title)).findFirst();
    }

//    @Override
    public boolean remove(Product product) {
        return shopList.remove(product);
    }

//    @Override
    public List<Product> getProductList() {
        // returns a copy of shopList
        return new ArrayList<>(shopList);
    }
}
