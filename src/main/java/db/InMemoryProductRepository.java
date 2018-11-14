package db;

import domain.Product;
import domain.ShoppingList;

import java.util.*;

public class InMemoryProductRepository implements ProductRepository {

    private List<Product> shopList;

    public InMemoryProductRepository() {
        shopList = new ArrayList<>();
    }

    @Override
    public void addToDataBase(Product product) {
        shopList.add(product);

    }

    @Override
    public Optional<Product> findByTitle(String title) {
        return shopList.stream().filter(product -> product.getTitle().equals(title)).findFirst();
    }

    @Override
    public boolean remove(Product product) {
        return shopList.remove(product);
    }

    @Override
    public List<Product> getProductList() {
        // returns a copy of shopList
        return new ArrayList<>(shopList);
    }
}
