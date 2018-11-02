package services;

import domain.Product;

import java.math.BigDecimal;

public class AddProductDetailsService {

    private ProductPriceService priceService;

    public AddProductDetailsService(ProductPriceService priceService) {
        this.priceService = priceService;
    }

    public void modifyProductTitle(String title, Product product) {
        product.setTitle(title);
    }

    public void modifyProductDescription(String description, Product product) {
        product.setDescription(description);
    }

    public void modifyProductPrice(double price, Product product) {
        BigDecimal priceToSet = priceService.returnValidatedPrice(price);
        product.setPrice(priceToSet);
    }

    public void modifyProductCategory(String category, Product product) {
        product.setCategory(category);
    }
}
