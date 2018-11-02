package db;

import domain.Product;

import java.util.*;

public class InMemoryDataBase implements Database {

    private List<Product> shopList;
    private Map<Integer, String> categories;

    public InMemoryDataBase() {
        shopList = new ArrayList<>();
        generateDefaultCategories();
    }

    @Override
    public void add(Product product) {
        shopList.add(product);

    }

    @Override
    public Optional<Product> findProductByTitle(String title) {
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

    @Override
    public void addCustomCategory(String categoryTitle) {
        categories.put(categories.size() + 1, categoryTitle);
    }

    public Map<Integer, String> getCategories() {
        return new HashMap<>(categories);
    }

    private void generateDefaultCategories() {
        categories = new HashMap<>();
        categories.put(categories.size() + 1, "Food");
        categories.put(categories.size() + 1, "Clothes");
        categories.put(categories.size() + 1, "Soft Drinks");
        categories.put(categories.size() + 1, "Alcohol");
        categories.put(categories.size() + 1, "Gadgets");
        categories.put(categories.size() + 1, "Other");
    }
}