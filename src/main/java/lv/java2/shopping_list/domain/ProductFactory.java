package lv.java2.shopping_list.domain;

import org.springframework.stereotype.Component;

@Component
public class ProductFactory {

    public Product createNewProductWithTitle(String title) {
        Product product = new Product();
        product.setTitle(title);
        return product;
    }
}
