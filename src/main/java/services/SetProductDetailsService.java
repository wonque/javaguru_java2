package services;

import domain.Product;

import java.math.BigDecimal;

public class SetProductDetailsService {


    public void modifyProductDescription(String description, Product product) {
        product.setDescription(description);
    }

    public void modifyProductPrice(BigDecimal productPrice, Product product) {
        product.setPrice(productPrice);
    }

    public void modifyProductCategory(String category, Product product) {
        product.setCategory(category);
    }

    public void modifyProductTitle(String title, Product product){
        product.setTitle(title);
    }

    public void modifyProductId(Long id, Product product) {
        product.setId(id);
    }
}
