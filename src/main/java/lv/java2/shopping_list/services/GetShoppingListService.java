package lv.java2.shopping_list.services;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetShoppingListService {

    private ProductRepository productRepository;

    public GetShoppingListService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getList() {
        return productRepository.getProductList();
    }
}
