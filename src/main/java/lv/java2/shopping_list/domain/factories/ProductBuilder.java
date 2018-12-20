package lv.java2.shopping_list.domain.factories;

import lv.java2.shopping_list.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductBuilder {

    public Product buildInstance(String title) {
        Product newProduct = new Product();
        newProduct.setTitle(title);
        return newProduct;
    }
}
