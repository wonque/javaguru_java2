package services;

import db.jdbc.ProductRepositoryImpl;
import domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class ProductPriceService {

    private ProductRepositoryImpl productRepository;

    public ProductPriceService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    public boolean update(String productTitle, double price) {
        BigDecimal priceToSet = returnDecimalPrice(price);
        Optional<Product> foundedProduct = productRepository.findByTitle(productTitle);
        if (foundedProduct.isPresent()) {
            Product product = foundedProduct.get();
            productRepository.updatePrice(product, priceToSet);
            return true;
        }
        return false;
    }

    private BigDecimal returnDecimalPrice(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    }
}
